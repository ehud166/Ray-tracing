package primitives;

/**
 * class that represent the color
 * (kredit to Samuel Finson, based on Dan Zilberstein version)
 */
public class Color {

	java.awt.Color _color;

	/**
	 * Default constructor - BLACK
	 */
	public Color() {
		_color = java.awt.Color.BLACK;
	}

	// *************** Constructors ****************** //

	/**
	 * c-tor with 3 base color and limits check
	 */
	public Color(int r, int g, int b) {

		//if color > 255 then set to 255
		r = Math.min(255, r);
		g = Math.min(255, g);
		b = Math.min(255, b);

		//if color < 0 then set to 0
		r = Math.max(0, r);
		g = Math.max(0, g);
		b = Math.max(0, b);

		_color = new java.awt.Color(r, g, b);
	}

	/**
	 * copy c-tor
	 */
	public Color(Color c) {

		int r = c._color.getRed();
		int g = c._color.getGreen();
		int b = c._color.getBlue();

		_color = new java.awt.Color(r, g, b);
	}

	// ********* Getters/Setters ************** //

	/**
	 * returns the "java.awt" color (not a copy)
	 * 
	 * @return color
	 */
	public java.awt.Color getColor() {
		return _color;
	}

	// ********* Operation ************ //

	/**
	 * Compare between two Colors
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Color))
			return false;

		if (this == null || obj == null) // one of them is null pointer
			return false;

		if (this == obj) // same pointers -> same objects
			return true;

		Color color = (Color) obj;
		return color.getColor().equals(_color);
	}

	/**
	 * add some colors to the color
	 * 
	 * @return color
	 */
	public Color add(Color... colors) {
		int r = _color.getRed();
		int g = _color.getGreen();
		int b = _color.getBlue();

		for (Color c : colors) {
			r += c.getColor().getRed();
			g += c.getColor().getGreen();
			b += c.getColor().getBlue();
		}

		_color = new Color(r, g, b).getColor();
		return this;
	}

	/**
	 * reduce some scalar from the color
	 */
	public void reduce(double num) {
		int r = (int) (_color.getRed() - num);
		int g = (int) (_color.getGreen() - num);
		int b = (int) (_color.getBlue() - num);

		_color = new Color(r, g, b).getColor();
	}

	/**
	 * mult the original color with a scalar
	 * 
	 * @return color
	 */
	public Color scale(double num) {

		int r = (int) (_color.getRed() * num);
		int g = (int) (_color.getGreen() * num);
		int b = (int) (_color.getBlue() * num);

		_color = new Color(r, g, b).getColor();
		return this;
	}

}
