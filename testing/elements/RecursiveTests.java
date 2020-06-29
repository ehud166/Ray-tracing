package testing.elements;

import org.junit.jupiter.api.Test;

import elements.Camera;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

class RecursiveTests {


	/**
	 * one sphere
	 */
	@Test
	public void recursiveTest() throws Exception {

		Scene scene = new Scene("Recursive Test");
		scene.setDistance(300);

		scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1)));

		Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000), new Color(0, 0, 100),
				new Material(1, 1, 20, 0, 0.4));
		scene.addGeometry(sphere);

		sphere = new Sphere(250, new Point3D(0.0, 0.0, -1000), new Color(100, 20, 20), new Material(1, 1, 20, 0, 0));
		scene.addGeometry(sphere);

		scene.addLight(new SpotLight(new Point3D(-200, -200, -150), 1, 0.00001, 0.0000008, new Color(255, 100, 100),
				new Vector(2, 2, -3)));

		ImageWriter imageWriter = new ImageWriter("Recursive Test", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();

	}

	/**
	 * one sphere with mirror triangle
	 */
	@Test
	public void recursiveTest2() throws Exception {

		Scene scene = new Scene("Recursive Test 2");
		scene.setDistance(300);

		scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1)));

		Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000), new Color(0, 0, 100),
				new Material(1, 1, 20, 0, 0.6));
		scene.addGeometry(sphere);

		sphere = new Sphere(150, new Point3D(-550, -500, -1000), new Color(100, 20, 20), new Material(1, 1, 20, 0, 0));
		scene.addGeometry(sphere);

		Triangle triangle = new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
				new Point3D(200, 200, -375), new Color(20, 20, 20), new Material(1, 1, 1, 1, 0));
		scene.addGeometry(triangle);

		triangle = new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
				new Point3D(-1500, -1500, -1500), new Color(20, 20, 20), new Material(1, 1, 1, 1, 0));
		scene.addGeometry(triangle);

		scene.addLight(new SpotLight(new Point3D(200, 200, -150), 1, 0.00001, 0.000005, new Color(255, 100, 100),
				new Vector(-2, -2, -3)));

		ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();

	}

	/**
	 * one sphere with 2 mirror triangle
	 */
	@Test
	public void recursiveTest3() throws Exception {

		Scene scene = new Scene("Recursive Test 3");
		scene.setDistance(300);

		scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1)));

		Sphere sphere = new Sphere(300, new Point3D(0, 0, -1000), new Color(0, 0, 100), new Material(1, 1, 20, 0, 0.5));
		scene.addGeometry(sphere);

		sphere = new Sphere(150, new Point3D(0, 0, -1000), new Color(100, 20, 20), new Material(1, 1, 20, 0, 0));
		scene.addGeometry(sphere);

		Triangle triangle = new Triangle(new Point3D(2000, -1000, -1500), new Point3D(-1000, 2000, -1500),
				new Point3D(700, 700, -375), new Color(20, 20, 20), new Material(1, 1, 1, 1, 0.5));
		scene.addGeometry(triangle);

		triangle = new Triangle(new Point3D(2000, -1000, -1500), new Point3D(-1000, 2000, -1500),
				new Point3D(-1000, -1000, -1500), new Color(20, 20, 20), new Material(1, 1, 1, 0.5, 0.5));
		scene.addGeometry(triangle);

		scene.addLight(new SpotLight(new Point3D(200, 200, -150), 1, 0.00001, 0.000005, new Color(255, 100, 100),
				new Vector(-2, -2, -3)));

		ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();

	}

}
