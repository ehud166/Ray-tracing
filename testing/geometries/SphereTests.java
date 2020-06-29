package testing.geometries;

import geometries.Geometry;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SphereTests {

	/**
	 * testing the "getNormal" function
	 */
	@Test
	void test_getNormal() {
		Point3D p = new Point3D(0, 0, 0);
		Sphere sphere = new Sphere(5, p, new Color(2, 5, 5), new Material(1.3, 1.4, 5));
		Point3D p1 = new Point3D(0, 0, 5);
		Vector v1 = sphere.getNormal(p1);
		Vector v2 = new Vector(0, 0, 5).normalize();
		assertEquals(v1, v2);
	}

	/**
	 * testing the "findIntersections" function
	 */
	@Test
	void test_findIntersections1() { // case of two intersection
		Sphere sphere = new Sphere(5, new Point3D(10, 0, 0), new Color(2, 5, 5), new Material(1.3, 1.4, 5));
		Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0));
		Map<Geometry, List<Point3D>> map = sphere.findIntersections(ray);
		Point3D p1 = new Point3D(5.0, 0.0, 0.0);
		Point3D p2 = new Point3D(15.0, 0.0, 0.0);
		// System.out.println("p1: "+points.get(0));
		// System.out.println("p2: "+points.get(1));
		assertTrue(map.get(sphere).get(0).equals(p1) && map.get(sphere).get(1).equals(p2));
	}

	/**
	 * testing the "findIntersections" function
	 */
	@Test
	void test_findIntersections2() { // case of no intersection
		Sphere sphere = new Sphere(5, new Point3D(10, 0, 0), new Color(2, 5, 5), new Material(1.3, 1.4, 5));
		Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0));
		Map<Geometry, List<Point3D>> map = sphere.findIntersections(ray);
		assertTrue(map.isEmpty());
	}

	/**
	 * testing the "findIntersections" function
	 */
	@Test
	void test_findIntersections3() { // case of one intersection
		Sphere sphere = new Sphere(5, new Point3D(12, 5, 0), new Color(2, 5, 5), new Material(1.3, 1.4, 5));
		Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0));
		Map<Geometry, List<Point3D>> map = sphere.findIntersections(ray);
		Point3D p = new Point3D(12.0, 0.0, 0.0);
		// System.out.print("p1: "+ points.get(0));
		assertTrue(map.get(sphere).get(0).equals(p) == true);
	}
}
