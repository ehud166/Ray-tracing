package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class for Sphere
 * (kredit to Shalom Rochman)
 */
public class Sphere extends RadialGeometry {

	Point3D _center;

	// *************** Constructors ****************** //

	/**
	 * c-tor
	 */
	public Sphere(double radius, Point3D point, Color emission, Material mat) {
		super(radius, emission, mat);
		_center = new Point3D(point);
	}

	/**
	 * copy c-tor
	 */
	public Sphere(Sphere mySphere) {
		super(mySphere);
		_center = new Point3D(mySphere._center);
	}

	// ************* Getters/Setters ****************** //

	/**
	 * returns the center point
	 * 
	 * @return point
	 */
	public Point3D getCenter() {
		return new Point3D(_center);
	}

	// ************* Operations **************** //

	@Override
	public Vector getNormal(Point3D point) {
		Vector v1 = new Vector(_center);
		Vector v2 = new Vector(point);
		return v2.sub(v1).normalize();
	}

	/**
	 * returns to string with detail
	 * of this sphere
	 * @return string
	 */
	@Override
	public String toString() {
		return "sphere: " + super.toString() + ", center: " + _center.toString();
	}

	/**
	 * returns map with the intersection points of sphere (if there is)
	 * 
	 * @return map
	 */
	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray v) {
		Vector u = _center.subtract(v.getPoo()); // the distance between ray's point to the center of the sphere
		double tm = u.dotProduct(v.getDirection()); // the distance between the ray and "Hagova" from the center of the
													// sphere

		double lentgh = u.getLength();
		double d = Math.sqrt(lentgh * lentgh - tm * tm); // d = sqrt of L^2-tm^2 (pytagoras)
		double r = getRadius();

		// 2 Tempt rows for the comparing
		Coordinate dd = new Coordinate(d);
		Coordinate rr = new Coordinate(r);
		Coordinate gap = dd.subtract(rr);

		Map<Geometry, List<Point3D>> map = new HashMap<Geometry, List<Point3D>>();
		if (gap.getCoordinate() > 0) // there are no intersection, the ray arn't touch the sphere
			return map;

		List<Point3D> points = new ArrayList<Point3D>();
		if (gap.getCoordinate() == 0)// there are one point of intersection, the ray is tangent to the sphere
		{
			Point3D p = v.getPoo().addVector(v.getDirection().scale(tm));
			points.add(p);
			map.put(this, points);
			return map;
		}

		// there are one point of intersection, the ray crosses the sphere
		double th = Math.sqrt(r * r - d * d); // to find d with a Pythagoras
		double t1 = tm - th;
		double t2 = tm + th;

		if (t1 >= 0) {
			Point3D p1 = v.getPoo().addVector(v.getDirection().scale(t1));
			points.add(p1);
		}

		if (t2 >= 0) {
			Point3D p2 = v.getPoo().addVector(v.getDirection().scale(t2));
			points.add(p2);
		}

		if (points != null && !points.isEmpty())
			map.put(this, points);

		return map;

	}

}
