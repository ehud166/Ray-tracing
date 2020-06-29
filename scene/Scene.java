package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * class representing scene
 * (kredit to Shalom Rochman
 */
public class Scene {

	String _name;
	Color _backgroundColor;
	AmbientLight _ambientLight;
	List<LightSource> _lights;
	Geometries _elements;
	Camera _camera;
	double _distance;

	// ************* Constructors ************** //

	/**
	 * c-tor of scene
	 * 
	 * @throws Exception
	 *             because there is "Vector" uses.
	 */
	public Scene(String name) throws Exception {
		_name = name;
		_backgroundColor = new Color();
		_elements = new Geometries();
		_lights = new ArrayList<LightSource>();
		_ambientLight = new AmbientLight(new Color(255, 255, 255), 0.1);//default ambient light is white
		_distance = 100;
	}

	// ********* Getters/Setters ************** //

	/**
	 * get the list of light
	 * 
	 * @return list of light
	 */
	public List<LightSource> getLights() {
		return _lights;
	}

	/**
	 * add a light source to the list of lights
	 */
	public void addLight(LightSource ls) {
		_lights.add(ls);
	}

	/**
	 * returns the background Color
	 * 
	 * @return color
	 */
	public Color getBackgroundColor() {
		return new Color(_backgroundColor);
	}

	/**
	 * set the background Color
	 */
	public void setBackgroundColor(Color newColor) {
		_backgroundColor = newColor;
	}

	/**
	 * returns the background Color
	 * 
	 * @return Ambient Light
	 */
	public AmbientLight getAmbientLight() {
		return new AmbientLight(_ambientLight);
	}

	/**
	 * set the background Color
	 */
	public void setAmbientLight(AmbientLight newColor) {
		_ambientLight = newColor;
	}

	/**
	 * returns the camera of the scene
	 * 
	 * @return Camera
	 */
	public Camera getCamera() {
		return new Camera(_camera);
	}

	/**
	 * set the camera of the scene
	 */
	public void setCamera(Camera newCamera) {
		_camera = newCamera;
	}

	/**
	 * returns the distance between the camera and the view plane
	 * 
	 * @return number
	 */
	public double getDistance() {
		return _distance;
	}

	/**
	 * set the distance between the camera and the view plane
	 */
	public void setDistance(double distance) {
		_distance = distance;
	}

	/**
	 * returns all the geometries in the scene
	 * 
	 * @return Geometries
	 */
	public Geometries getGeometries() {
		return _elements;
	}

	// ********* Operation ************ //

	/**
	 * add a geometry to the scene
	 */
	public void addGeometry(Geometry newGeometry) {
		_elements.addGeometry(newGeometry);
	}

}
