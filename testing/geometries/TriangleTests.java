package testing.geometries;

import geometries.Geometry;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TriangleTests {

	/**
	 * checks one and no intersections with triangle
	 * 
	 * @throws Exception
	 */
	@Test
	void test_findIntersections() throws Exception {

		Point3D p1 = new Point3D(5, 5, 0);
		Point3D p2 = new Point3D(5, -5, 0);
		Point3D p3 = new Point3D(5, 0, 5);
		Triangle tr = new Triangle(p1, p2, p3, new Color(2, 5, 5), new Material(1.3, 1.4, 5));

		Vector direction = new Vector(5, -2, 3).normalize();
		Ray r = new Ray(new Point3D(0, 0, 0), direction);

		Map<Geometry, List<Point3D>> map = tr.findIntersections(r);
		assertEquals(map.get(tr).get(0), new Point3D(5, -2, 3));

		// checks case with no intersection points
		map.clear();
		direction = new Vector(5, 5, 5).normalize();
		r = new Ray(new Point3D(0, 0, 0), direction);
		map.putAll(tr.findIntersections(r));
		assertTrue(map.isEmpty());
	}

}
