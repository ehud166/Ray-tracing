package elements;

import primitives.Color;

/**
 * abstract class that represent a light element on the scene
 */
public abstract class Light {

	Color _color;

	// *************** Constructors ****************** //

	/**
	 * c-tor of Light
	 */
	public Light(Color color) {
		_color = new Color(color);
	}

	/**
	 * copy c-tor of Light
	 */
	public Light(Light light) {
		_color = new Color(light._color);
	}

	// ********* Getters/Setters ************** //

	/**
	 * the function return a copy  of the color so it won't change
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return new Color(_color);
	}

	// ************* Operations **************** //

	/**
	 * the function return the intensity of the light
	 * 
	 * @return color of intensity
	 */
	public Color getIntensity() {
		return new Color(_color);
	};

}
