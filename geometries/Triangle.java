package geometries;

import primitives.*;

import java.util.List;
import java.util.Map;

/**
 * Represents a Triangle
 * (kredit to Shalom Rochman)
 */
public class Triangle extends Plane {

	Point3D _p1;
	Point3D _p2;
	Point3D _p3;

	// *************** Constructors ****************** //

	/**
	 * c-tor
	 * 
	 * @throws Exception
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3, Color emission, Material mat) throws Exception {
		super(p1, p2, p3, emission, mat);
		_p1 = new Point3D(p1);
		_p2 = new Point3D(p2);
		_p3 = new Point3D(p3);
	}

	/**
	 * copy c-tor of triangle
	 */
	public Triangle(Triangle tri) {
		super(tri);
		_p1 = new Point3D(tri._p1);
		_p2 = new Point3D(tri._p2);
		_p3 = new Point3D(tri._p3);
	}

	// ************* Getters/Setters ****************** //

	/**
	 * returns copy of the first point
	 * 
	 * @return point p1
	 */
	public Point3D getP1() {
		return new Point3D(_p1);
	}

	/**
	 * returns copy of the second point
	 * 
	 * @return point p2
	 */
	public Point3D getP2() {
		return new Point3D(_p2);
	}

	/**
	 * returns copy of the third point
	 * 
	 * @return point p3
	 */
	public Point3D getP3() {
		return new Point3D(_p3);
	}

	// ************* operations **************** //

	@Override
	public Vector getNormal(Point3D p) {
		return new Vector(_normal); // the triangle is also a plane :)
	}

	/**
	 * returns a string that represent the Triangle
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "triangle: " + _p1.toString() + ", " + _p2.toString() + ", " + _p3.toString() + "\n";
	}

	/**
	 * returns the intersection points if exists
	 * 
	 * @return map
	 */
	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {

		Map<Geometry, List<Point3D>> map = super.findIntersections(ray);
		if (map == null || map.isEmpty())
			return map; // there is no intersection point

		Vector v1 = _p1.subtract(ray.getPoo());
		Vector v2 = _p2.subtract(ray.getPoo());
		Vector v3 = _p3.subtract(ray.getPoo());

		Vector n1 = v1.crossProduct(v2);
		Vector n2 = v2.crossProduct(v3);
		Vector n3 = v3.crossProduct(v1);

		Vector tmp = map.get(this).get(0).subtract(ray.getPoo());
		double s1 = tmp.dotProduct(n1);
		double s2 = tmp.dotProduct(n2);
		double s3 = tmp.dotProduct(n3);

		if (s1 >= 0 && s2 >= 0 && s3 >= 0 || s1 <= 0 && s2 <= 0 && s3 <= 0)
			return map;

		map.clear();
		return map; // intersection point not in the triangle
	}

}
