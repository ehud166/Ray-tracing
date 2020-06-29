package geometries;

import primitives.*;

import java.util.List;
import java.util.Map;

/**
 * Represents a geometry shape
 * (kredit to Shalom Rochman)
 */
public abstract class Geometry {

	Color _emission;  //geometry self color
	Material _material;

	// *************** Constructors ****************** //

	/**
	 * default c-tor of geometry
	 */
	public Geometry() {
		_emission = new Color();
	}

	/**
	 * copy c-tor of geometry
	 */
	public Geometry(Geometry other) {
		_emission = new Color(other._emission);
		_material = new Material(other._material);
	}

	// ********* Getters/Setters ************** //

	/**
	 * gets a copy the material of the geometry
	 * 
	 * @return color
	 */
	public Material getMaterial() {
		return new Material(_material);
	}

	/**
	 * gets a copy the emission color of the geometry
	 * 
	 * @return color
	 */
	public Color getEmission() {
		return new Color(_emission);
	}

	// ************* operations **************** //

	/**
	 * the copy of normal vector that go through the point
	 * 
	 * @return Vector
	 */
	public abstract Vector getNormal(Point3D point) throws Exception;

	/**
	 * returns all the intersection points of "ray" with a geometry shape
	 * 
	 * @return map
	 */
	public abstract Map<Geometry, List<Point3D>> findIntersections(Ray ray) throws Exception;

}
