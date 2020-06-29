package elements;

import primitives.*;

/**
 * class of the camera
 * (kredit to Samuel Finson)
 */
public class Camera {
	Point3D _p0;
	Vector _vUp;
	Vector _vTo;
	Vector _vRi;

	// *************** Constructors ****************** //

	/**
	 * @param p0
	 *            is the position of the camera
	 * @param vUp
	 *            is the vector up
	 * @param vTo
	 *            is the vector to
	 * @throws Exception
	 *             in case that the vectors arn't orthogonal
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) throws Exception {

		//orthogonal check
		if (vUp.dotProduct(vTo) != 0)
			throw new Exception("ERROR: This is not orthogonal");

		_vUp = vUp.normalize();
		_vTo = vTo.normalize();
		_vRi = _vUp.crossProduct(_vTo);
		// _vright normalized
		_p0 = new Point3D(p0);
	}

	/**
	 * copy constructor of camera
	 */
	public Camera(Camera _camera) {
		_p0 = _camera._p0;
		_vUp = _camera._vUp;
		_vTo = _camera._vTo;
		_vRi = _camera._vRi;

	}

	/**
	 * default c-tor
	 *
	 * @throws Exception
	 */
	public Camera() throws Exception {
		this(new Point3D(0, 0, 0), //center point
				new Vector(0, 1, 0), //vUp
				new Vector(0, 0, -1)); //vTo
	}

	// ************* Getters/Setters ****************** //

	/**
	 * returns the vector toward
	 * 
	 * @return vector
	 */
	public Vector get_vTo() {
		return new Vector(_vTo);
	}

	/**
	 * returns the vector up
	 * 
	 * @return vector
	 */
	public Vector get_vUp() {
		return new Vector(_vUp);
	}

	/**
	 * returns the vector right
	 * 
	 * @return vector
	 */
	public Vector get_vRi() {
		return new Vector(_vRi);
	}

	/**
	 * returns the P0 points
	 * 
	 * @return p0
	 */
	public Point3D get_p0() {
		return new Point3D(_p0);
	}

	// ************* operations **************** //

	/**
	 * @param distance
	 *            - the distance for the view plane
	 * @param width
	 *            - the width of the view plane
	 * @param height
	 *            - the height of the view plane
	 * @param nx
	 *            - how many pixels in X axis
	 * @param ny
	 *            - how many pixels in Y axis
	 * @param i
	 *            - pixel place in X axis
	 * @param j
	 *            - pixel place in Y axis
	 * @return the ray the goes through that pixel (i,j)
	 */
	public Ray constructRayThroughPixel(double distance, double width, double height, double nx, double ny, int i, int j) {

		/* not necsasery because my operators return new Vector sometimes even normalized
		Vector vToward = new Vector(_vTo);
		Vector vRight = new Vector(_vRi);
		Vector vUP = new Vector(_vUp);

		vToward.normalize();
		vRight.normalize();
		vUP.normalize();
		*/

		// Calculating the image center
		Point3D pc = _p0.addVector(_vTo.scale(distance));
		double rx = width / nx;
		double ry = height / ny;

		double xi = (i - (nx / 2)) * rx - (rx / 2);
		double yj = (j - (ny / 2)) * ry - (ry / 2);

		Point3D pij = pc.addVector(_vRi.scale(xi).sub(_vUp.scale(yj)));
		Vector vij = pij.subtract(_p0);

		return new Ray(_p0, vij);

	}
}
