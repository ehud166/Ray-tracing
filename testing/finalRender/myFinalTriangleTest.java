package testing.finalRender;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Plane;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class myFinalTriangleTest {

	/**
	 * build scene with some triangles
	 * @throws Exception
	 */
    @Test
    public void myFinalTriangleTest_v1() throws Exception {
        Scene scene = new Scene("myFinalTriangleTest_v1");
        scene.setDistance(5500);
        scene.setAmbientLight(new AmbientLight(new Color(20,0,20), 0.5));

		Triangle triangle= new Triangle(new Point3D(0, 200,-1000),new Point3D(-100, 0,-1000),new Point3D(100, 0,-1000),new Color(50,0,0),new Material(0.1,5,29,0,0.5,1,0));
		scene.addGeometry(triangle);
		triangle= new Triangle(new Point3D(-100, 0,-1000),new Point3D(100, 0,-1000),new Point3D(0, -200,-1000),new Color(0,50,0),new Material(0.1,5,29,0,0.5,1,0.5));
		scene.addGeometry(triangle);
		triangle= new Triangle(new Point3D(-200, -200,-1000),new Point3D(0, -200,-1000),new Point3D(-100, 0,-1000),new Color(0,0,50),new Material(0.1,5,29,0,0.5,1,0));
		scene.addGeometry(triangle);
		triangle= new Triangle(new Point3D(100, 0,-1000),new Point3D(0, -200,-1000),new Point3D(200, -200,-1000),new Color(50,0,50),new Material(0.1,5,29,0,0.5,1,0));
		scene.addGeometry(triangle);
		//******************triangle up opossite
		triangle= new Triangle(new Point3D(0, 200,-1000),new Point3D(-100, 400,-1000),new Point3D(100, 400,-1000),new Color(0,50,50),new Material(0.1,5,29,0,0.5,1,0));
		scene.addGeometry(triangle);
		triangle= new Triangle(new Point3D(0, 600,-1000),new Point3D(-100, 400,-1000),new Point3D(100, 400,-1000),new Color(0,50,50),new Material(0.1,5,29,0,0.5,1,0));
		scene.addGeometry(triangle);
		triangle= new Triangle(new Point3D(0, 600,-1000),new Point3D(-100, 400,-1000),new Point3D(-200, 600,-1000),new Color(0,50,50),new Material(0.1,5,29,0,0.5,1,0));
		scene.addGeometry(triangle);
		triangle= new Triangle(new Point3D(0, 600,-1000),new Point3D(100, 400,-1000),new Point3D(200, 600,-1000),new Color(0,50,50),new Material(0.1,5,29,0,0.5,1,0));
		scene.addGeometry(triangle);
		triangle= new Triangle(new Point3D(200, 200,-1000),new Point3D(0, 200,-1000),new Point3D(100, 0,-1000),new Color(0,50,50),new Material(0.1,5,29,0,0.5,1,0));
		scene.addGeometry(triangle);


        Plane plane = new Plane(new Point3D(0, -200, 0), new Vector(0, 1, 0), new Color(0, 0, 0),
                new Material(3, 4, 19, 1, 0, 1, 0));
        scene.addGeometry(plane); // floor

        scene.addLight(new SpotLight(new Point3D(-1000, 2000, -3000), 1, 0.00001, 0.0000005, new Color(150, 150, 250),
                new Vector(0.5, 2, 1)));

        scene.setCamera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));
        ImageWriter imageWriter = new ImageWriter("myFinalTriangleTest_v1", 800, 800, 800, 800);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}
