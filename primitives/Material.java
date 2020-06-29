package primitives;

/**
 * class that save the definitions of a geometry in the scene (like shininess,and more Attenuation constants etc. )
 * (kredit to Shalom Rochman)
 */
public class Material {
	double _Kd;//mekadem diffuse
	double _Ks;//mekadem specullar
	double _Kr;//mekadem reflection
	double _Kt;//mekadem refraction
	int nShininess;//bohak
	double Kglossy;//improve: mekadem glossy surface
	double Kmatt;//improve: mekdaem diffuse glass

	// *************** Constructors ****************** //
	//due to refactoring we have extend c-tor every time that we wanted to add field

	/**
	 * c-tor of Material with mat. the parameters must be in the range: [0,1]
	 */
	public Material(double Kd, double Ks, int myNShininess, double Kr, double Kt, double Kglossy, double kMat) {
		this(Kd, Ks, myNShininess,Kr, Kt,Kglossy);
		/*
		_Kd = Kd;
		_Ks = Ks;
		_Kr = Kr;
		_Kt = Kt;
		nShininess = myNShininess;
		Kglossy = Kglossy;
		*/
		Kmatt = kMat;
	}

	/**
	 * c-tor of Material with Kglossy. the parameters must be in the range: [0,1]
	 */
	public Material(double Kd, double Ks, int myNShininess, double Kr, double Kt, double Kglossy) {
		this(Kd, Ks, myNShininess,Kr, Kt);
		/*
		_Kd = Kd;
		_Ks = Ks;
		_Kr = Kr;
		_Kt = Kt;
		Kmatt = 0;
		nShininess = myNShininess;
		*/
		this.Kglossy = Kglossy;
	}

	/**
	 * c-tor of Material. the parameters must be in the range: [0,1]
	 */
	public Material(double Kd, double Ks, int myNShininess, double Kr, double Kt) {
		this(Kd, Ks, myNShininess);
		/*
		_Kd = Kd;
		_Ks = Ks;
		Kglossy = 1;
		Kmatt = 0;
		nShininess = myNShininess;
		*/
		_Kr = Kr;
		_Kt = Kt;
	}

	/**
	 * c-tor of Material the. parameters must be in the range: [0,1]
	 */
	public Material(double Kd, double Ks, int myNShininess) {
		_Kd = Kd;
		_Ks = Ks;
		_Kr = 0;
		_Kt = 0;
		Kglossy = 0;//for more Kglossy 1
		Kmatt = 1;//for more mat 0
		nShininess = myNShininess;
	}

	/**
	 * copy c-tor of Material. the parameters must be in the range: [0,1]
	 */
	public Material(Material mat) {
		_Kd = mat._Kd;
		_Ks = mat._Ks;
		_Kr = mat._Kr;
		_Kt = mat._Kt;
		Kglossy = mat.Kglossy;
		Kmatt = mat.Kmatt;
		nShininess = mat.nShininess;
	}

	// ********* Getters/Setters ************** //

	/**
	 * returns the value of nShininess
	 * 
	 * @return number
	 */
	public int getNShininess() {
		return nShininess;
	}

	/**
	 * returns the value of Ks - specular
	 * 
	 * @return number
	 */
	public double getKs() {
		return _Ks;
	}

	/**
	 * returns the value of Kd - diffuse
	 * 
	 * @return number
	 */
	public double getKd() {
		return _Kd;
	}

	/**
	 * returns the value of Kr - reflection
	 * 
	 * @return number
	 */
	public double getKr() {
		return _Kr;
	}

	/**
	 * returns the value of Kt - refracted
	 * 
	 * @return number
	 */
	public double getKt() {
		return _Kt;
	}

	/**
	 * return the Kglossy level of the geometry. NOTE: it will be in the range [0,1]
	 */
	public double getKglossy() {
		return Kglossy;
	}

	/**
	 * return the mat level of the geometry. NOTE: it will be in the range [0,1]
	 */
	public double getKMat() {
		return Kmatt;
	}

	/**
	 * set the Kglossy level of the geometry. NOTE: it will be in the range [0,1]
	 * @param kglossy
	 */
	public void setKglossy(double kglossy) {
		this.Kglossy = kglossy;
	}

	/**
	 * set the Kmat level of the geometry. NOTE: it will be in the range [0,1]
	 * @param kmatt
	 */
	public void setKmatt(double kmatt) {
		this.Kmatt = kmatt;
	}
}
