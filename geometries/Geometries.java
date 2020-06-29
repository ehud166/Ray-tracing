package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * implementation of the abstract class "geometry"
 * (kredit to Yohanan Haik)
 */
public class Geometries extends Geometry {
	List<Geometry> _elements;

	// *************** Constructors ****************** //
	/**
	 * c-tor of Geometries
	 */
	public Geometries() {
		_elements = new ArrayList<Geometry>();
	}

	// ************* Getters/Setters ****************** //

	/**
	 * do nothing just because he`s extend the Geometry
	 * @return null
	 */
	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	/**
	 * returns all the geometries
	 * 
	 * @return list
	 */
	public List<Geometry> getGeometries() {
		return _elements;
	}

	// ************* operations **************** //

	/**
	 * add a geometry to elements
	 */
	public void addGeometry(Geometry newGeometry) {
		_elements.add(newGeometry);
	}

	/**
	 * returns all the intersection points of "ray" with all the geometries
	 * 
	 * @return map
	 */
	@Override
	public Map<Geometry, List<Point3D>> findIntersections(Ray ray) throws Exception {
		Map<Geometry, List<Point3D>> map = new HashMap<Geometry, List<Point3D>>();

		for (Geometry g : _elements) {
			Map<Geometry, List<Point3D>> tmp = g.findIntersections(ray);
			if (tmp.values() != null && !tmp.isEmpty())
				map.putAll(tmp);
		}
		return map;
	}

}
