package primitives;

/**
 * A class representing Point2D
 * (kredit to Samuel Finson)
 */
public class Point2D {
	Coordinate _x;
	Coordinate _y;

	// ************* Constructors ************** //

	/**
	 * default c-tor
	 * (x,y)=(0,0)
	 */
	public Point2D() {
		this._x=new Coordinate();
		this._y=new Coordinate();
	}
	/**
	 * c-tor of Point2D
	 */
	public Point2D(double myX, double myY) {
		_x = new Coordinate(myX);
		_y = new Coordinate(myY);
	}

	/**
	 * copy c-tor of Point2D
	 */
	public Point2D(Point2D myPoint2D) {
		_x = new Coordinate(myPoint2D._x);
		_y = new Coordinate(myPoint2D._y);
	}

	// ********* Getters/Setters ************** //

	/**
	 * sets the x coordinate
	 */
	public void setX(double myX, double myY) {
		_x = new Coordinate(myX);
	}

	/**
	 * sets the y coordinate
	 */
	public void setY(double myX, double myY) {
		_y = new Coordinate(myY);
	}

	/**
	 * return the value of the coordinate x
	 */
	public double getX() {
		return _x.getCoordinate();
	}

	/**
	 * return the value of the coordinate y
	 */
	public double getY() {
		return _y.getCoordinate();
	}

	// ********* Administration ************ //

	/**
	 * Compare between two points
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Point2D))
			return false;

		if (this == null || obj == null) // one of them is null pointer
			return false;

		if (this == obj) // same pointers -> same objects
			return true;

		Point2D point2D = (Point2D) obj;
		return _x.equals(point2D._x) && _y.equals(point2D._y);
	}

	/**
	 * print point in format (x,y)
	 * 
	 * @return string
	 */
	public String toString() {
		return "(" + _x + "," + _y + ")";
	}

}