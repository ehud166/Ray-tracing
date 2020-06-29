package testing.geometries;

import geometries.Geometry;
import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaneTests {

	/**
	 * testing the "getNormal" function
	 */
	@Test
	void test_getNormal() {
		Point3D p1 = new Point3D(1, 1, 1);
		Point3D p2 = new Point3D(2, 2, 2);
		Point3D p3 = new Point3D(3, 4, 5);

		try {
			Plane pl = new Plane(p1, p2, p3, new Color(1, 1, 1), new Material(1.3, 1.4, 5));
			Vector normal = pl.getNormal(p2);
			Vector answer = new Vector(-1, 2, -1).normalize();

			boolean b1 = answer.equals(normal);
			boolean b2 = answer.scale(-1).equals(normal);

			assertTrue(b1 || b2);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}

	}

	/**
	 * in here we checked the correctly of a intersection point in plane
	 */
	@Test
	void test_findIntersections() {
		Point3D p1 = new Point3D(1, 1, 1);
		Point3D p2 = new Point3D(2, 2, 2);
		Point3D p3 = new Point3D(3, 4, 5);

		try {
			Plane pl = new Plane(p1, p2, p3, new Color(2, 5, 5), new Material(1.3, 1.4, 5));
			Ray r = new Ray(new Point3D(0, 0, 0), new Vector(5, 10, 5));

			Map<Geometry, List<Point3D>> map = pl.findIntersections(r);
			assertEquals(map.get(pl).get(0), new Point3D(0, 0, 0));
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}

	/**
	 * in here we checked a case with no any intersection points
	 */
	@Test
	void test_findIntersections_2() {

		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(2, 0, 0);
		Point3D p3 = new Point3D(0, 4, 0);

		try {
			Plane pl = new Plane(p1, p2, p3, new Color(2, 5, 5), new Material(1.3, 1.4, 5));
			Ray r = new Ray(new Point3D(0, 0, 5), new Vector(6, 5, 0));

			Map<Geometry, List<Point3D>> map = pl.findIntersections(r);
			assertTrue(map.isEmpty());
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}

	}

}
