package testing.renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RenderTest {

	/**
	 * checks that the function "getReflectedVector" are good
	 *
	 * @throws Exception
	 */
	@Test
	public void test_getReflectedVector() throws Exception {
		Vector n = new Vector(0, 1, 0).normalize();
		Vector l = new Vector(1, -1, 0).normalize();

		Scene scene = new Scene("Test scene");
		ImageWriter imageWriter = new ImageWriter("reflected test", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		Vector r = render.getReflectedVector(l, n);
		assertEquals(r, new Vector(1, 1, 0).normalize());
	}

	/**
	 * test that we imported from moodle
	 *
	 * @throws Exception
	 */
	@Test
	public void basicRendering() throws Exception {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistance(150);
		scene.addGeometry(new Sphere(50, new Point3D(0, 0, 150), new Color(90, 90, 90), new Material(1.3, 1.4, 5)));

		scene.addGeometry(new Triangle(new Point3D(100, 0, 149), new Point3D(0, 100, 149), new Point3D(100, 100, 149),
				new Color(250, 0, 0), new Material(1.3, 1.4, 5)));

		scene.addGeometry(new Triangle(new Point3D(100, 0, 149), new Point3D(0, -100, 149), new Point3D(100, -100, 149),
				new Color(0, 250, 0), new Material(1.3, 1.4, 5)));

		scene.addGeometry(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, 100, 149), new Point3D(-100, 100, 149),
				new Color(0, 0, 250), new Material(1.3, 1.4, 5)));

		scene.addGeometry(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(-100, -100, 149), new Color(90, 90, 90), new Material(1.3, 1.4, 5)));

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50);
		render.writeToImage();
	}

	/**
	 *
	 * test that the function are good
	 *
	 * @throws Exception
	 */
	@Test
	public void Test_getClosestPoint() throws Exception {

		ImageWriter im = new ImageWriter("test0", 500, 500, 500, 500);
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		Render r = new Render(im, scene);

		List<Point3D> points = new ArrayList<Point3D>();
		points.add(new Point3D(5, 5, 5));
		points.add(new Point3D(2, 2, 2));

		Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();
		intersectionPoints.put(new Sphere(50, new Point3D(0, 0, 0), new Color(2, 5, 5), new Material(1.3, 1.4, 5)),
				points);

		Point3D p = r.getClosestPoint(intersectionPoints).entrySet().iterator().next().getValue();
		assertEquals(p, new Point3D(2, 2, 2));

		// another case
		points.clear();
		intersectionPoints.clear();
		points.add(new Point3D(50, 0, 0));
		points.add(new Point3D(30, 0, 0));
		intersectionPoints.put(new Sphere(30, new Point3D(0, 0, 0), new Color(2, 5, 5), new Material(1.3, 1.4, 5)),
				points);

		p = r.getClosestPoint(intersectionPoints).entrySet().iterator().next().getValue();
		assertEquals(p, new Point3D(30, 0, 0));

	}



}