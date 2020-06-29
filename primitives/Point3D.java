package primitives;

/**
 * A class representing Point3D
 * (kredit to Samuel Finson)
 */
public class Point3D extends Point2D {

	Coordinate _z;

	// ************* Constructors ************** //

	/**
	 * default c-tor
	 * (x,y,z)=(0,0,0)
	 */
	public Point3D() {
		super();
		this._z=new Coordinate();
	}
	/**
	 * c-tor
	 */
	public Point3D(double x, double y, double z) {
		super(x, y);
		_z = new Coordinate(z);
	}

	/**
	 * copy c-tor
	 */
	public Point3D(Point3D myPoint) {
		super(myPoint.getX(), myPoint.getY());
		_z = new Coordinate(myPoint._z);
	}

	// ********* Getters/Setters ************** //

	/**
	 * return the value of the coordinate z
	 */
	public double getZ() {
		return _z.getCoordinate();
	}

	/**
	 * sets the z coordinate
	 */
	public void setZ(double myZ) {
		_z = new Coordinate(myZ);
	}

	// ********* Administration ************ //

	/**
	 * print Point3D in format (x,y,z)
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "(" + _x + "," + _y + "," + _z + ")";
	}

	/**
	 * compare between two 3D points
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Point3D))
			return false;

		if (this == null || obj == null) // one of them is null pointer
			return false;

		if (this == obj) // same pointers -> same objects
			return true;

		Point3D p3 = (Point3D) obj;
		return super.equals(obj) && _z.equals(p3._z);
	}

	// ********* Operation ************ //

	/**
	 * add vector to point3D
	 * 
	 * @param myVec
	 *            is the vector that we will add to the point
	 * @return new point that represent the add of the vector
	 */
	public Point3D addVector(Vector myVec) {
		Point3D p3 = myVec.getPoint();
		double x = getX() + p3.getX();
		double y = getY() + p3.getY();
		double z = getZ() + p3.getZ();
		return new Point3D(x, y, z);
	}

	/**
	 * returns a copy of the subtract between two 3D points
	 * 
	 * @param other
	 *            is the other point that we need to subtract from our point
	 * @return subtraction vector
	 */
	public Vector subtract(Point3D other) {
		Point3D p = other.mult(-1);
		Point3D p2 = addVector(new Vector(p));
		return new Vector(p2);
	}

	/**
	 * calculate the distance between two 3D points
	 * 
	 * @return length
	 */
	public double distance(Point3D other) {
		return subtract(other).getLength();
	}

	/**
	 * multiply the point in some number return a copy of the new point
	 * 
	 * @return point
	 */
	public Point3D mult(double num) {
		double x = getX() * num;
		double y = getY() * num;
		double z = getZ() * num;

		return new Point3D(x, y, z);
	}

}