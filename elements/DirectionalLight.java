package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for light with direction (sun, etc.)
 *(kredit to Samuel Finson)
 *
 */
public class DirectionalLight extends Light implements LightSource {

	Vector _direction;

	// *************** Constructors ****************** //

	/**
	 * c-tor of DirectionalLight
	 */
	public DirectionalLight(Vector direction, Color color) {
		super(color);
		_direction = direction.normalize();
	}

	/**
	 * copy c-tor of DirectionalLight
	 */
	public DirectionalLight(DirectionalLight light) {
		super(light);
		_direction = new Vector(light._direction);
	}

	// ************* Operations **************** //

	/**
	 * the function return the intensity of the light
	 *
	 * @return color of intensity
	 */
	@Override
	public Color getIntensity(Point3D p) {
		return getIntensity();
	}

	/**
	 * in this case all the points have the same directional vector because the
	 * source located at the infinity
	 */
	@Override
	public Vector getL(Point3D p) {
		return new Vector(_direction);
	}

	/**
	 * the function return the direction vector of the light source
	 *
	 * @return direction vector
	 */
	@Override
	public Vector getD(Point3D p) {
		return new Vector(_direction);
	}

}
