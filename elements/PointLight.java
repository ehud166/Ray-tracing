package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class represent point light source
 * (kredit to Samuel Finson)
 */
public class PointLight extends Light implements LightSource {

	Point3D _position;
	double _Kc, _Kl, _Kq;

	// *************** Constructors ****************** //

	/**
	 * c-tor of PointLight
	 * 
	 * @param kc
	 *            must be >= 1
	 * @throws Exception
	 *             if "kc" is less than 1
	 */
	public PointLight(Point3D p, double kc, double kl, double kq, Color color) throws Exception {
		super(color);
		_position = new Point3D(p);
		if (kc < 1)
			throw new Exception("ERORR: Kc must be equal or bigger then 1 ");
		if (kl < 0 || kq < 0)
			throw new Exception("ERORR: Kl and kq must be positive ");

		_Kc = kc;
		_Kl = kl;
		_Kq = kq;
	}

	/**
	 * copy c-tor of PointLight
	 */
	public PointLight(PointLight p) {
		super(p);
		_position = new Point3D(p._position);
		_Kc = p._Kc;
		_Kl = p._Kl;
		_Kq = p._Kq;
	}

	// ************* Operations **************** //

	/**
	 * the function return the intensity of the light
	 *
	 * @return color of intensity
	 */
	@Override
	public Color getIntensity(Point3D p) {
		double d = _position.distance(p);
		double mechane = _Kc + _Kl * d + _Kq * d * d;
		return getIntensity().scale(1 / mechane);
	}

	/**
	 * the function return the vector that hit the point p
	 *
	 * @return distance vector
	 */
	@Override
	public Vector getL(Point3D p) {
		return p.subtract(_position).normalize();
	}

	/**
	 * the function return the direction vector of the light source
	 *
	 * @return vector
	 */
	@Override
	public Vector getD(Point3D p) {
		return getL(p);
	}

}
