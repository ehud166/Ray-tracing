package renderer;

import elements.LightSource;
import geometries.Geometry;
import primitives.*;
import scene.Scene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class for create image from the scene
 * (kredit to Ori Bibi, Yohanan Haik, Jacob Fridman, raffael knoll)
 */
public class Render {

	private static final int MAX_CALC_COLOR_LEVEL = 7;//the level recurssion
	private static final int TIMES_OF_RAYS = 5;//amount of ray
	ImageWriter _imageW;
	Scene _scene;

	// ************* Constructors ************** //

	/**
	 * c-tor of the render
	 */
	public Render(ImageWriter imageWriter, Scene scene) {
		_imageW = imageWriter;
		_scene = scene;
	}

	// ************* Operations **************** //

	/**
	 * render the image
	 */
	public void renderImage() throws Exception {

		for (int i = 0; i < _imageW.getNx(); i++) {
			for (int j = 0; j < _imageW.getNy(); j++) {

				Ray ray = _scene.getCamera().constructRayThroughPixel(_scene.getDistance(), _imageW.getWidth(), _imageW.getHeight(),
						_imageW.getNx(), _imageW.getNy(), i, j);
				//make al list with all the intersection
				Map<Geometry, List<Point3D>> intersectionPoints = _scene.getGeometries().findIntersections(ray);

				if (intersectionPoints == null || intersectionPoints.isEmpty())//if there is no intesection
					_imageW.writePixel(i, j, _scene.getBackgroundColor().getColor());//give the backgroung color

				else {//there is one or more intersection
					Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);//calc which intesection closest
					Geometry g = closestPoint.entrySet().iterator().next().getKey();
					_imageW.writePixel(i, j, calcColor(g, closestPoint.get(g), ray).getColor());//for each pixel calc the pixel color
				}

			}
		}

	}

	/**
	 * explain later in the improved calcColor
	 * @param g
	 * @param p
	 * @param ray
	 * @return call to the extend function with the amount of the level of calc color demanding
	 */
	private Color calcColor(Geometry g, Point3D p, Ray ray) throws Exception {
		return calcColor(g, p, ray, MAX_CALC_COLOR_LEVEL, 1);
	}

	/**
	 * calculate the color for the demand pixel
	 * @param g - the geometry intersected for getting more information on the point intersected
	 * @param p - the point intersected
	 * @param inRay - the ray hited the geometry
	 * @param level - the demanding level
	 * @param k - the starting level
	 * @return the exactly color fit to this pixel
	 */
	private Color calcColor(Geometry g, Point3D p, Ray inRay, int level, double k) throws Exception {
		if (level == 0 || Util.isZero(k))// check if require no level to calc so that return black color
			return new Color();//black

		Color color = _scene.getAmbientLight().getIntensity();
		color.add(g.getEmission());

		Vector n = g.getNormal(p);//normal of geometry
		int nShininess = g.getMaterial().getNShininess();
		double kd = g.getMaterial().getKd();
		double ks = g.getMaterial().getKs();

		for (LightSource lightSource : _scene.getLights()) {
			Vector l = lightSource.getL(p);
			double ln = l.dotProduct(n);
			Vector v = inRay.getDirection();
			double vn = v.dotProduct(n);
			if (ln * vn > 0) { // checks if they in the same surface & we can see the geometry
				double o = occluded(l, p, g);//extend: mekadem shadow
				if (!Util.isZero(o * k)) {
					Color lightIntensity = new Color(lightSource.getIntensity(p)).scale(o);

					color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}

		// Recursive call for reflected ray
		Ray reflectedRay = constructReflectedRay(n, p, inRay);
		double kr = g.getMaterial().getKr();
		Color reflectedLight = calcColor_mini(reflectedRay, kr, level, k);//the simple color reflected

		double constant = g.getMaterial().getKglossy(); // the more glossy, the more clearly mirror
		reflectedLight = calcRaysDiffuse(reflectedRay, constant, inRay.getDirection(),
				reflectedLight, k * kr, level);//improve the simple one

		// Recursive call for refracted ray
		Ray refractedRay = constructRefractedRay(p, inRay);
		double kt = g.getMaterial().getKt();
		Color refractedLight = calcColor_mini(refractedRay, kt, level, k);//the simple color refracted

		constant = 1 - g.getMaterial().getKMat(); // the less matt the more transparent
		refractedLight = calcRaysDiffuse(refractedRay, constant, reflectedRay.getDirection(),
				refractedLight, k * kt, level);//improve the simple one

		return color.add(reflectedLight, refractedLight);//the final color
	}

	/**
	 * The function returns the average color according to 5 rays in the "outRay"
	 * direction
	 * 
	 * @param outRay
	 *            - The direction vector from the geometry and out
	 *            (reflection\refraction)
	 * 
	 * @param vertical
	 *            - The normal of the outRay (camera or reflection vector )
	 * 
	 * @param constant
	 *            - The power of the glossy or the matt
	 * 
	 * @param refLight
	 *            - The reflected\refracted light to consider with the average of
	 *            the 5 rays
	 * 
	 * @param level
	 *            - Level of recursion
	 * 
	 * @param k
	 *            - Global constant for decreasing (with the level of the recursion)
	 *            * (kr or kt)
	 *
	 * @return Final color considering glossy\matt effect
	 */
	private Color calcRaysDiffuse(Ray outRay, double constant, Vector vertical, Color refLight, double k, int level) throws Exception {

		double wideness = 1 - constant; // suppose we give 1 to glossy it`ll be mirror
		if (Util.isZero(wideness)) // there is no glossy or matt effect
			return refLight; // all the rays in the same direction - save render time because it isn`t necassery

		//ray hited
		Point3D p = outRay.getPoo();
		Vector direction = outRay.getDirection();

		int r = refLight.getColor().getRed();
		int g = refLight.getColor().getGreen();
		int b = refLight.getColor().getBlue();
		wideness = wideness * 0.02; // determine the angle so if it more bigger it more diffuser

		for (int i = 0; i < TIMES_OF_RAYS; i++) {
			if (!Util.isZero(wideness)) {
				Vector epsilon = vertical.scale(wideness); // power of diffusion
				Ray r1 = new Ray(p, direction.add(epsilon));
				Ray r2 = new Ray(p, direction.add(epsilon.scale(-1))); // -1 to calc the returns ray from the geometry

				epsilon = vertical.crossProduct(direction).scale(wideness); // 2 other directions (up\down)
				Ray r3 = new Ray(p, direction.add(epsilon));
				Ray r4 = new Ray(p, direction.add(epsilon.scale(-1))); // -1 to calc the returns ray from the geometry

				Color l1 = calcColor_mini(r1, 1, level, k); // 1 because we are not need to decrease the light
				Color l2 = calcColor_mini(r2, 1, level, k);// "
				Color l3 = calcColor_mini(r3, 1, level, k);// "
				Color l4 = calcColor_mini(r4, 1, level, k);// "

				r += l1.getColor().getRed() + l2.getColor().getRed() + l3.getColor().getRed() + l4.getColor().getRed();
				g += l1.getColor().getGreen() + l2.getColor().getGreen() + l3.getColor().getGreen()
						+ l4.getColor().getGreen();
				b += l1.getColor().getBlue() + l2.getColor().getBlue() + l3.getColor().getBlue()
						+ l4.getColor().getBlue();
				wideness = wideness * 0.9;
			}
		}

		r = r / (1 + 4 * TIMES_OF_RAYS);//calcs the average
		g = g / (1 + 4 * TIMES_OF_RAYS);// "
		b = b / (1 + 4 * TIMES_OF_RAYS);// "

		return new Color(r, g, b);
	}

	/**
	 * Help function that calcs the color per a ray and level considering a
	 * constants (kt\ks)
	 * 
	 * @param inRay
	 *            The outgoing ray (like camera ray, reflective & refraction ray)
	 * 
	 * @param constant
	 *            Constant for decreasing
	 * 
	 * @param level
	 *            The level of the recursion
	 * 
	 * @param k
	 *            Recursion's constant for decreasing
	 * 
	 * @return Color
	 */
	private Color calcColor_mini(Ray inRay, double constant, int level, double k) throws Exception {


		Map<Geometry, List<Point3D>> intersectionPoints = _scene.getGeometries().findIntersections(inRay);
		Map<Geometry, Point3D> point = getClosestPoint(intersectionPoints);

		if (point.isEmpty())
			return new Color();//black
		else {
			Geometry g2 = point.entrySet().iterator().next().getKey();
			Point3D p2 = point.entrySet().iterator().next().getValue();
			return calcColor(g2, p2, inRay, level - 1, k * constant).scale(constant);
		}
	}

	/**
	 * calculating the refracted ray
	 * 
	 * @param inRay  the vector of the camera
	 * @return Refracted ray
	 */
	private Ray constructRefractedRay(Point3D p, Ray inRay) {
		Vector epsilon = inRay.getDirection();
		Point3D rayPoint = p.addVector(epsilon.scale(2));

		return new Ray(rayPoint, epsilon);
	}

	/**
	 * calculating the reflected ray
	 * NOTE: the point of the ray will be more farther than the point "p"
	 * 
	 * @param inRay the vector of the camera
	 * @return Reflected Ray
	 */
	private Ray constructReflectedRay(Vector n, Point3D p, Ray inRay) {
		Vector direction = getReflectedVector(inRay.getDirection(), n);
		Point3D rayPoint = p.addVector(direction.scale(2)); // add epsilon
		return new Ray(rayPoint, direction);
	}

	/**
	 * checks if the point is occluded that mean the point is blocked by another Geometry from diffuse light
	 * 
	 * @return the mekadem of shadow if the point not Completely sealed
	 */
	private double occluded(Vector l, Point3D point, Geometry geometry) throws Exception {

		Vector lightDirection = l.scale(-1);//the direction is from the Geometry to the light source
		Vector epsVector = geometry.getNormal(point);

		// checking the direction of the triangl's normal
		if (epsVector.dotProduct(lightDirection) < 0)
			epsVector = epsVector.scale(-2);
		else
			epsVector = epsVector.scale(2);

		Point3D geometryPoint = point.addVector(epsVector);

		Ray lightRay = new Ray(geometryPoint, lightDirection);
		Map<Geometry, List<Point3D>> intersectionPoints = _scene.getGeometries().findIntersections(lightRay);

		//extend: the problem that if the Geometry is a little transparent it doesn`t make a shadow
		double Kshadow = 1;
		for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet())
			Kshadow *= entry.getKey().getMaterial().getKt();//even if the Kt is a little transparent i will give him shadow

		return Kshadow;//return double value improving the boolean condition
	}

	/**
	 * calculate the specular light
	 * 
	 * @return color
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		v = v.scale(-1);//the ray returns from the geometry

		Vector r = getReflectedVector(l, n);
		double vr = v.dotProduct(r);

		if (vr <= 0) // checks if they in the same surface
			return new Color();//black

		double temp = ks * Math.pow(vr, nShininess);
		return new Color(lightIntensity).scale(temp);
	}

	/**
	 * calculating the reflected vector.
	 * 
	 * NOTE: l,n must be normalized!
	 * 
	 * @param l
	 *            is the coming vector to reflect
	 * @param n
	 *            vector to reflect on
	 * 
	 * @return normalized reflected vector
	 */
	public Vector getReflectedVector(Vector l, Vector n) {
		double ln = l.dotProduct(n);
		return l.add(n.scale(-2 * ln)); // the reflected vector
	}

	/**
	 * calculate the diffusive light
	 * 
	 * @return the color after scaling the mekadem with the l*n
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double tmp = kd * Math.abs(l.dotProduct(n));
		return new Color(lightIntensity).scale(tmp);
	}

	/**
	 * Returns the closest point to the camera
	 * 
	 * @return map
	 */
	public Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {

		Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();

		if (intersectionPoints == null || intersectionPoints.isEmpty())//if there isn`t intersection
			return closestPoint;

		Point3D center = _scene.getCamera().get_p0();
		double minDistance = Double.MAX_VALUE;

		for (Geometry g : intersectionPoints.keySet()) {
			List<Point3D> tmpList = intersectionPoints.get(g);
			for (Point3D p : tmpList) {
				double tmpLength = p.distance(center);
				if (tmpLength < minDistance) {
					closestPoint.clear();
					closestPoint.put(g, p);
					minDistance = tmpLength;
				}
			}

		}

		return closestPoint;

	}

	/**
	 * create a grid in the image
	 * 
	 * @param size
	 *            mean to size*size pixels per square
	 */
	public void printGrid(int size) {

		// paints pixels of columns.
		for (int x = 0; x < _imageW.getNx(); x += size)
			for (int y = 0; y < _imageW.getNy(); y++) {
				_imageW.writePixel(x, y, 255, 255, 255);
			}

		// paints pixels of rows
		for (int y = 0; y < _imageW.getNy(); y += size)
			for (int x = 0; x < _imageW.getNx(); x++) {
				_imageW.writePixel(x, y, 255, 255, 255);
			}
	}

	/**
	 * write the data into the image
	 */
	public void writeToImage() {
		_imageW.writeToimage();
	}
}