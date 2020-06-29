package geometries;

import primitives.Color;
import primitives.Material;

/**
 * Represents a Radial Geometry
 * (kredit to Shalom Rochman)
 */
public abstract class RadialGeometry extends Geometry {

	double _radius;

	// *************** Constructors ****************** //

	/**
	 * c-tor of radial geometry
	 */
	public RadialGeometry(double radius, Color emission, Material mat) {
		_emission = new Color(emission);
		_material = new Material(mat);
		_radius = radius;
	}

	/**
	 * copy c-tor of radial geometry
	 */
	public RadialGeometry(RadialGeometry other) {
		super(other);
		_radius = other._radius;
	}

	// ************* Getters/Setters ****************** //

	/**
	 * returns the radius
	 * 
	 * @return number
	 */
	public double getRadius() {
		return _radius;
	}

	// ************* Operations **************** //

	/**
	 * returns a RadialGeometry that represent the Cylinder
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "radius: " + _radius;
	}

}
