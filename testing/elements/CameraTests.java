package testing.elements;

import elements.Camera;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class CameraTests {

	/**
	 * checks not orthogonal vectors. assume to be exception
	 * 
	 * @throws Exception
	 */
	@Test
	void testCTor1() {
		Point3D center = new Point3D(0, 0, 0);
		Vector vUp = new Vector(1, 0, 0);
		Vector vTo = new Vector(1, 0, 0);
		try {
			Camera c = new Camera(center, vUp, vTo);
			System.out.println("1 - ERROR ");

		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * checks the third vector. needs to be what we have calculate on paper
	 */
	@Test
	void testCTor2() {
		Point3D center = new Point3D(0, 0, 0);
		Vector vUp = new Vector(0, 0, 1);
		Vector vTo = new Vector(1, 0, 0);
		try {
			Camera c = new Camera(center, vUp, vTo);
			assertEquals(c.get_vRi(), new Vector(0, 1, 0));
		} catch (Exception e) {
			System.out.println("2 - ERROR");
			assertTrue(false);
		}
	}

	/**
	 * test the correctly of random ray in a scene using the "Scene" class
	 */
	@Test
	void testRay() {

		try {

			Point3D center = new Point3D(5, 5, 5);
			Vector vUp = new Vector(0, 0, 1);
			Vector vTo = new Vector(1, 0, 0);
			Camera cam = new Camera(center, vUp, vTo);

			double distance = 100;
			double width = 150;
			double height = 150;
			int nx = 3;
			int ny = 3;
			int i = 3;
			int j = 3;

			Scene s = new Scene("check");
			s.setCamera(cam);
			s.setDistance(distance);

			Ray ray = s.getCamera().constructRayThroughPixel(s.getDistance(), width, height, nx, ny, i, j);
			Ray ray2 = new Ray(new Point3D(5, 5, 5), new Vector(100, 50, -50).normalize());

			assertEquals(ray, ray2);
		} catch (Exception e) {
			System.out.println("3 - ERROR ");
		}

	}

	/**
	 * test the correctly of a ray in a scene with many options of view points
	 */
	@Test
	void testRay2() {

		try {

			Point3D p0 = new Point3D(0, 0, 0);
			Vector vUp = new Vector(0, 0, 1);
			Vector vTo = new Vector(1, 0, 0);
			Camera cam = new Camera(p0, vUp, vTo);

			double distance = 100;
			double width = 150;
			double height = 150;

			// first we will check correctly with both even # of pixels in Y axis
			int nx = 4;
			int ny = 4;
			int i = 2;
			int j = 2;

			Ray ray = cam.constructRayThroughPixel(distance, width, height, nx, ny, i, j);
			Ray ray2 = new Ray(new Point3D(0, 0, 0), new Vector(100, -18.75, 18.75).normalize());

			assertEquals(ray, ray2);

			// Secondary we will check correctly with odd pixels in Y axis
			nx = 4;
			ny = 3;
			i = 3;
			j = 3;

			ray = cam.constructRayThroughPixel(distance, width, height, nx, ny, i, j);
			ray2 = new Ray(new Point3D(0, 0, 0), new Vector(100, 18.75, -50).normalize());

			assertEquals(ray, ray2);

			// in addition we will check correctly with odd pixels in X axis
			nx = 3;
			ny = 4;
			i = 1;
			j = 1;

			ray = cam.constructRayThroughPixel(distance, width, height, nx, ny, i, j);
			ray2 = new Ray(new Point3D(0, 0, 0), new Vector(100, -50, 56.25).normalize());

			assertEquals(ray, ray2);
		} catch (Exception e) {
			System.out.println("4 - ERROR ");
		}

	}

}
