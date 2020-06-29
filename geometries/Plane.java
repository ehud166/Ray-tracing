package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the class Represents a plane
 * (kredit to Yohanan Haik)
 */
public class Plane extends Geometry implements FlatGeometry {

	Point3D _point;
	Vector _normal;

	// *************** Constructors ****************** //

	/**
	 * c-tor of the plane
	 * 
	 * @throws Exception
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3, Color emission, Material mat) throws Exception {
		_emission = new Color(emission);
		_material = new Material(mat);
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		Vector normal = v1.crossProduct(v2);

		// checks if the points in the same line
		if (normal == new Vector(0, 0, 0))
			throw new Exception("ERROR: the points are in the same line!");

		// checks if there is any couple of same points
		if (p1 == p2 || p1 == p3 || p2 == p3)
			throw new Exception("ERROR: the are 2 same points!");

		_normal = normal.normalize();
		_point = new Point3D(p1); // point in the normal vector

	}

	/**
	 * c-tor of the plane
	 */
	public Plane(Point3D point, Vector verticalVector, Color emission, Material mat) {
		_emission = new Color(emission);
		_point = new Point3D(point);
		_material = new Material(mat);
		_normal = verticalVector.normalize(); // for any problem that may show up
	}

	/**
	 * copy c-tor of the plane
	 */
	public Plane(Plane other) {
		super(other);
		_point = new Point3D(other._point);
		_normal = new Vector(other._normal);
	}

	// ************* Getters/Setters ****************** //

	/**
	 * returns a copy of the point
	 * 
	 * @return point
	 */
	public Point3D getPoint() {
		return new Point3D(_point);
	}

	// ************* operations **************** //

	@Override
	public Vector getNormal(Point3D p) {
		return new Vector(_normal);
	}

	/**
	 * returns a string that represent the Plane
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "point: " + _point.toString() + ", vertical vector: " + _normal.toString();
	}

	/**
	 * returns the intersection points if exists
	 * 
	 * @return map
	 */
	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {

		Map<Geometry, List<Point3D>> map = new HashMap<Geometry, List<Point3D>>();
		double check = _normal.dotProduct(ray.getDirection());
		if (check == 0)
			return map; // there is no intersection points because they are parallel

		double mone = _normal.dotProduct(_point.subtract(ray.getPoo()));
		double mechane = _normal.dotProduct(ray.getDirection());
		double t = mone / mechane;

		if (t < 0)
			return map;

		Point3D inter = ray.getPoo().addVector(ray.getDirection().scale(t));

		List<Point3D> answer = new ArrayList<Point3D>();
		answer.add(inter);

		map.put(this, answer);
		return map;
	}

}
