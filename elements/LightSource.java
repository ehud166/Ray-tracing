
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * an interface that represent a light source on the scene
 * implementing by point&spot&directional light
 */
public interface LightSource {

	// ************* Operations **************** //

	/**
	 * the function return the intensity of the light source just a copy
	 * 
	 * @return color
	 */
	public Color getIntensity(Point3D p);

	/**
	 * the function return the vector that hit the point p
	 * 
	 * @return vector
	 */
	public Vector getL(Point3D p);

	/**
	 * the function return the direction vector of the light source
	 * 
	 * @return vector
	 */
	public Vector getD(Point3D p);

}
