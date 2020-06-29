package testing.elements;

import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

class LightsTests {

	/**
	 * test the spot light in a position with 2 spheres
	 */
	@Test
	public void many_spheres_Test() throws Exception {

		Scene scene = new Scene("many spheres");
		Sphere sphere = new Sphere(800, new Point3D(0, 0, -1000), new Color(0, 0, 100), new Material(2, 3, 20));
		scene.addGeometry(sphere);

		sphere = new Sphere(300, new Point3D(0, 0, -400), new Color(200, 0, 0), new Material(2, 3, 20));
		scene.addGeometry(sphere);

		scene.addLight(new SpotLight(new Point3D(-200, -200, -100), 1, 0.00001, 0.000005, new Color(255, 100, 100),
				new Vector(2, 2, -3)));

		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("many spheres", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * another test of point light
	 *
	 * @throws Exception
	 */



	@Test
	public void test_pointLight() throws Exception {
		Scene myScene = new Scene("sphere");

		Triangle first = new Triangle(new Point3D(3500, 3500, -2000), new Point3D(-3500, -3500, -2000),
				new Point3D(3500, -3500, -2000), new Color(), new Material(1, 1, 1));

		Triangle second = new Triangle(new Point3D(3500, 3500, -2000), new Point3D(-3500, 3500, -2000),
				new Point3D(-3500, -3500, -2000), new Color(), new Material(1, 1, 1));

		Sphere third = new Sphere(800, new Point3D(0, 0, -1000), new Color(0, 0, 100), new Material(1, 1, 20));

		myScene.addGeometry(first);
		myScene.addGeometry(second);
		myScene.addGeometry(third);

		Camera camera = new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1));
		myScene.setCamera(camera);
		myScene.setDistance(100);

		PointLight pl = new PointLight(new Point3D(200, 200, -100), 1, 0.000001, 0.0000005, new Color(255, 100, 100));
		myScene.addLight(pl);

		ImageWriter sceneWriter = new ImageWriter("point test 2", 500, 500, 500, 500);
		Render myRender = new Render(sceneWriter, myScene);

		myRender.renderImage();
		myRender.writeToImage();
	}

	/**
	 * test the spot light in a position with 2 spheres
	 */
	@Test
	public void many_spheres_Test2() throws Exception {

		Scene scene = new Scene("test spot");
		Sphere sphere = new Sphere(800, new Point3D(1000, 0, 0), new Color(0, 0, 100), new Material(2, 3, 20));
		scene.addGeometry(sphere);

		sphere = new Sphere(300, new Point3D(400, -100, 0), new Color(200, 0, 0), new Material(2, 3, 20, 0, 0.5));
		scene.addGeometry(sphere);

		scene.addLight(new SpotLight(new Point3D(100, -200, 200), 1, 0.00001, 0.000005, new Color(255, 100, 100),
				new Vector(2, 2, -3)));

		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(1, 0, 0)));

		ImageWriter imageWriter = new ImageWriter("many spheres 2", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * checks the checks the spot light on 2 triangles
	 */
	@Test
	public void test_spotLight() throws Exception {
		Scene myScene = new Scene("triangles with spot light");

		Triangle first = new Triangle(new Point3D(3500, 3500, -2000), new Point3D(-3500, -3500, -1000),
				new Point3D(3500, -3500, -2000), new Color(), new Material(4, 3, 5));

		Triangle second = new Triangle(new Point3D(3500, 3500, -2000), new Point3D(-3500, 3500, -1000),
				new Point3D(-3500, -3500, -1000), new Color(), new Material(4, 3, 5));

		myScene.addGeometry(first);
		myScene.addGeometry(second);

		Camera camera = new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1));
		myScene.setCamera(camera);
		myScene.setDistance(100);

		SpotLight mySpotLight = new SpotLight(new Point3D(200, 200, -100), 1, 0.000001, 0.0000005,
				new Color(255, 100, 100), new Vector(-2, -2, -3));
		myScene.addLight(mySpotLight);

		ImageWriter sceneWriter = new ImageWriter("spot test 3", 500, 500, 500, 500);
		Render myRender = new Render(sceneWriter, myScene);

		myRender.renderImage();
		myRender.writeToImage();
	}

	/**
	 * test the spot light in a position
	 */
	@Test
	public void spotLightTest() throws Exception {

		Scene scene = new Scene("test spot");
		Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(1, 1, 20));
		scene.addGeometry(sphere);
		scene.addLight(new SpotLight(new Point3D(-200, -200, -100), 1, 0.00001, 0.000005, new Color(255, 100, 100),
				new Vector(2, 2, -3)));

		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("Spot test", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * test the point light in a position
	 */
	@Test
	public void Test_pointLight() throws Exception {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1)));
		scene.setDistance(100);

		scene.addLight(new PointLight(new Point3D(-200, -200, -100), 2, 0.000001, 0.0000005, new Color(255, 100, 100)));

		Sphere s = new Sphere(800, new Point3D(0, 0, -1000), new Color(0, 0, 100), new Material(2, 3, 20));
		scene.addGeometry(s);

		ImageWriter imageWriter = new ImageWriter(" Point Test", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * tests spot & point light
	 *
	 * @throws Exception
	 */
	@Test
	public void Test_spotAndPointLight() throws Exception {

		Scene scene = new Scene("test spot");
		Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(1, 1, 20));
		scene.addGeometry(sphere);

		scene.addLight(new SpotLight(new Point3D(-200, -200, -100), 1, 0.00001, 0.000005, new Color(0, 100, 250),
				new Vector(2, 2, -3)));

		PointLight pl = new PointLight(new Point3D(200, 200, -100), 1, 0.000001, 0.0000005, new Color(255, 0, 0));
		scene.addLight(pl);

		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("Spot & point test", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();

	}

	/**
	 * triangle shadow on a sphere
	 *
	 * @throws Exception
	 */
	@Test
	public void spotLightTest2() throws Exception {

		Scene scene = new Scene("shadow");
		scene.setDistance(200);
		Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(1, 1, 20));
		scene.addGeometry(sphere);

		Triangle triangle = new Triangle(new Point3D(-125, -225, -260), new Point3D(-225, -125, -260),
				new Point3D(-225, -225, -270), new Color(0, 0, 100), new Material(1, 1, 4));
		scene.addGeometry(triangle);

		scene.addLight(new SpotLight(new Point3D(-200, -200, -150), 1, 0.00001, 0.000005, new Color(255, 100, 100),
				new Vector(2, 2, -3)));

		Camera camera = new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1));
		scene.setCamera(camera);

		ImageWriter imageWriter = new ImageWriter("Spot test 2 ", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();

	}

	/**
	 * sphere on triangle with shadow
	 *
	 * @throws Exception
	 */
	@Test
	public void shadowTest() throws Exception {

		Scene scene = new Scene("shadow2");
		Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000), new Color(0, 0, 100), new Material(1, 1, 21));
		scene.addGeometry(sphere);

		Triangle triangle = new Triangle(new Point3D(3500, 3500, -2000), new Point3D(-3500, -3500, -1000),
				new Point3D(3500, -3500, -2000), new Color(), new Material(1, 1, 3));

		Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000), new Point3D(-3500, 3500, -1000),
				new Point3D(-3500, -3500, -1000), new Color(), new Material(1, 1, 3));

		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);

		scene.addLight(new SpotLight(new Point3D(200, 200, -100), 1, 0.000001, 0.0000005, new Color(255, 100, 100),
				new Vector(-2, -2, -3)));

		Camera camera = new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1));
		scene.setCamera(camera);

		ImageWriter imageWriter = new ImageWriter("shadow test", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();

	}
}
