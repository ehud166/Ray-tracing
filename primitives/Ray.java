package primitives;

/**
 * A class representing Ray
 * (kredit to Samuel Finson)
 */
public class Ray {
	Point3D _point;
	Vector _direction;

	// *************** Constructors ****************** //

	/**
	 * c-tor of Ray
	 */
	public Ray(Point3D point, Vector direction) {
		_point = new Point3D(point);
		_direction = direction.normalize();
	}

	/**
	 * copy c-tor of Ray
	 */
	public Ray(Ray b2) {
		_point = new Point3D(b2._point);
		_direction = new Vector(b2._direction);
	}

	// ************* Getters/Setters ****************** //

	/**
	 * returns a copy of the direction
	 * 
	 * @return direction vector
	 */
	public Vector getDirection() {
		return new Vector(_direction);
	}

	/**
	 * returns a copy of the ray point
	 * 
	 * @return point
	 */
	public Point3D getPoo() {
		return new Point3D(_point);
	}

	// ************* Administration **************** //

	/**
	 * print the ray
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "ray point = " + _point + ", direction = " + _direction;
	}

	/**
	 * compare between rays
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Ray)) // not the same kind of variables
			return false;

		if (this == null || obj == null) // one of them is null pointer
			return false;

		if (this == obj) // same pointers -> same objects
			return true;

		Ray r = (Ray) obj;
		boolean b1 = _point.equals(r.getPoo());
		boolean b2 = _direction.equals(r.getDirection());
		return b1 && b2;
	}

}
