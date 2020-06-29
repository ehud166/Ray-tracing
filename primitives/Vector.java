package primitives;

/**
 * A class representing Vector
 * (kredit to Samuel Finson)
 */
public class Vector {

	Point3D _head;

	// ************* Constructors ****************** //

	/**
	 * c-tor of Vector
	 */
	public Vector(double x, double y, double z) {
		_head = new Point3D(x, y, z);
	}

	/**
	 * c-tor of Vector
	 */
	public Vector(Point3D myPoint3D) {
		_head = new Point3D(myPoint3D);
	}

	/**
	 * copy c-tor of Vector
	 */
	public Vector(Vector myVec) {
		_head = new Point3D(myVec._head);
	}

	// ***** Getters/Setters ********** //

	/**
	 * returns a copy of the point
	 * 
	 * @return head point
	 */
	public Point3D getPoint() {
		return new Point3D(_head);
	}

	/**
	 * sets values to the point
	 */
	public void setPoint(double x, double y, double z) {
		_head = new Point3D(x, y, z);
	}

	// ***** Administration ******** //

	/**
	 * print the vector (in format of point)
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return _head.toString();
	}

	/**
	 * compare between vectors
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Vector))
			return false;

		if (this == null || obj == null) // one of them is null pointer
			return false;

		if (this == obj) // same pointers -> same objects
			return true;

		Vector vec = (Vector) obj;
		return _head.equals(vec._head);
	}

	// ********* Operation ************ //

	/**
	 * add 2 vectors. return a copy
	 * 
	 * @return the add vector
	 */
	public Vector add(Vector vec) {
		return new Vector(_head.addVector(vec));
	}

	/**
	 * subtract vectors/ return a copy
	 * 
	 * @return the subtract vector
	 */
	public Vector sub(Vector vec) {
		Vector newVec = vec.scale(-1);
		return add(newVec);
	}

	/**
	 * returns a copy of the multiplication with a number
	 * 
	 * @return Vector
	 */
	public Vector scale(double num) {
		return new Vector(_head.mult(num));
	}

	/**
	 * returns the length of the vector
	 * 
	 * @return length
	 */
	public double getLength() {
		double x = Math.pow(_head.getX(), 2);
		double y = Math.pow(_head.getY(), 2);
		double z = Math.pow(_head.getZ(), 2);
		return Math.sqrt(x + y + z);
	}

	/**
	 * a copy of the Unit Vector
	 * 
	 * @return unit vector
	 */
	public Vector normalize() {
		double len = getLength();

		double x = _head.getX() / len;
		double y = _head.getY() / len;
		double z = _head.getZ() / len;

		return new Vector(x, y, z);
	}

	/**
	 * returns copy of the Vector that represent the result of the unit Vector
	 * Product
	 * 
	 * @return vector
	 */
	public Vector crossProduct(Vector vec) {

		Point3D p1 = new Point3D(_head);
		Point3D p2 = vec.getPoint();

		double x = p1.getY() * p2.getZ() - p2.getY() * p1.getZ();
		double y = -(p1.getX() * p2.getZ() - p2.getX() * p1.getZ());
		double z = p1.getX() * p2.getY() - p2.getX() * p1.getY();

		return new Vector(x, y, z).normalize();
	}

	/**
	 * calculate the scalar Product
	 * 
	 * @return number
	 */
	public double dotProduct(Vector vec) {

		Point3D p1 = new Point3D(_head);
		Point3D p2 = vec.getPoint();

		double x = p1.getX() * p2.getX();
		double y = p1.getY() * p2.getY();
		double z = p1.getZ() * p2.getZ();

		return x + y + z;
	}
}