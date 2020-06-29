package elements;

import primitives.Color;

/**
 * class for the ambient light
 */
public class AmbientLight extends Light {

	double Ka = 0.1;

	// *************** Constructors ****************** //

	/**
	 * c-tor for ambient light
	 */
	public AmbientLight(Color color, double my_ka) {
		super(color.scale(my_ka));
		Ka = my_ka;
	}

	/**
	 * copy c-tor for ambient light
	 */
	public AmbientLight(AmbientLight amb) {
		super(amb);
		Ka = amb.Ka;
	}

	// ********* Getters/Setters ************** //

	/**
	 * the function returns the Ka of the ambient light
	 * 
	 * @return number Ka
	 */
	public double getKa() {
		return Ka;
	}

}
