
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class that represent spot light source
 */
public class SpotLight extends PointLight {

	Vector _direction;

	// *************** Constructors ****************** //
	/**
	 * c-tor of SpotLight
	 * 
	 * @throws Exception
	 */
	public SpotLight(Point3D p, double kc, double kl, double kq, Color color, Vector direction) throws Exception {
		super(p, kc, kl, kq, color);
		_direction = new Vector(direction).normalize();
	}

	/**
	 * copy c-tor of SpotLight
	 */
	public SpotLight(SpotLight s) {
		super(s);
		_direction = new Vector(s._direction).normalize();
	}

	// ************* Operations **************** //

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
