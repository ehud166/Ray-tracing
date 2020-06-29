package testing.finalRender;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
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

/**
 * test class to represent our final sphere scene out the space
 */
public class myFinalSphereTest {

    /**
     * build scene with 5 spheres
     * insert pointLight into the yellow sphere
     * insert pointLight into the clear/deamon sphere
     * insert spotLight all over the scene
     * v1- an light purple look
     * after improve: Kglossy is 0.9
     *
     * @throws Exception
     */
    @Test
    public void myFinalSphereTest_v1() throws Exception {
        Scene scene = new Scene("myFinalSphereTest_v1");
        scene.setDistance(5500);
        scene.setAmbientLight(new AmbientLight(new Color(20,0,20), 0.5));

        Sphere sphere = new Sphere(200, new Point3D(-400, 0, -1200), new Color(0, 0, 50),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // blue left

        sphere = new Sphere(100, new Point3D(100, -100, -1000), new Color(), new Material(0.1, 5, 29, 1, 0.5, 1, 0));
        scene.addGeometry(sphere); // black center
        scene.addLight(new PointLight(new Point3D(100, -150, -1000), 2, 0.000001, 0.0000005, new Color(150, 150, 150))); //light in sphere

        sphere = new Sphere(150, new Point3D(600, -50, -1200), new Color(0, 0, 0),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // black left

        sphere = new Sphere(150, new Point3D(-100, -50, -1500), new Color(50, 0, 0),
                new Material(0.1, 5, 29, 0.7, 0, 1, 0));
        scene.addGeometry(sphere); // red center

        sphere = new Sphere(100, new Point3D(-250, -100, -1800), new Color(0, 50, 0),
                new Material(0.1, 5, 29, 0, 0, 1, 0));
        //scene.addGeometry(sphere); // green left

        sphere = new Sphere(300, new Point3D(500, 100, -2000), new Color(80, 70, 0),
                new Material(0.1, 5, 29, 0.6, 0.4, 1, 0));
        scene.addGeometry(sphere); // yellow big

        scene.addLight(new PointLight(new Point3D(500, 100, -2000), 2, 0.000001, 0.0000005, new Color(10, 10, 10))); //light in sphere

        Plane plane = new Plane(new Point3D(0, -200, 0), new Vector(0, 1, 0), new Color(0, 0, 0),
                new Material(3, 4, 19, 1, 0, 0.9, 0));
        scene.addGeometry(plane); // floor

        scene.addLight(new SpotLight(new Point3D(-1000, 2000, -3000), 1, 0.00001, 0.0000005, new Color(150, 150, 250),
                new Vector(0.5, 2, 1)));

        scene.setCamera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));
        ImageWriter imageWriter = new ImageWriter("myFinalSphereTest_v1", 800, 800, 800, 800);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * build scene with 5 spheres
     * insert pointLight into the yellow sphere
     * insert pointLight into the clear/deamon sphere
     * insert spotLight all over the scene
     * v2- an light purple look - more clearly
     * after improve: Kglossy is 0.9
     *
     * @throws Exception
     */
    @Test
    public void myFinalSphereTest_v2() throws Exception {
        Scene scene = new Scene("myFinalSphereTest_v2");
        scene.setDistance(5500);
        scene.setAmbientLight(new AmbientLight(new Color(5,0,5), 0.5));

        Sphere sphere = new Sphere(200, new Point3D(-400, 0, -1200), new Color(0, 0, 50),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // blue left

        sphere = new Sphere(100, new Point3D(100, -100, -1150), new Color(), new Material(0.1, 5, 29, 1, 0.5, 1, 0));
        scene.addGeometry(sphere); // black center
        scene.addLight(new PointLight(new Point3D(100, -150, -1150), 2, 0.000001, 0.0000005, new Color(180, 180, 180))); //light in sphere

        sphere = new Sphere(150, new Point3D(600, -50, -1200), new Color(0, 0, 0),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // black sphere

        sphere = new Sphere(150, new Point3D(-100, -50, -1500), new Color(65, 0, 0),
                new Material(0.1, 5, 29, 0.7, 0.1, 1, 0));
        scene.addGeometry(sphere); // red sphere

        sphere = new Sphere(300, new Point3D(500, 100, -2000), new Color(95, 70, 0),
                new Material(0.1, 5, 29, 0.6, 0.4, 1, 0));
        scene.addGeometry(sphere); // yellow sphere
        //light in the yellow sphere
        scene.addLight(new PointLight(new Point3D(600, 100, -2000), 2, 0.000001, 0.0000005, new Color(10, 10, 10))); //light in sphere


        Plane plane = new Plane(new Point3D(0, -200, 0), new Vector(0, 1, 0), new Color(0, 0, 0),
                new Material(3, 4, 19, 0.75, 0, 0.9, 0));
        scene.addGeometry(plane); // floor

        scene.addLight(new SpotLight(new Point3D(-1000, 2000, -3000), 1, 0.00001, 0.0000005, new Color(150, 150, 250),
                new Vector(0.5, 2, 1)));

        scene.setCamera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));
        ImageWriter imageWriter = new ImageWriter("myFinalSphereTest_v2", 800, 800, 800, 800);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * build scene with 5 spheres
     * insert pointLight into the yellow sphere
     * insert pointLight into the clear/deamon sphere
     * insert spotLight all over the scene
     * v3- clearly scene deamon balls
     * after improve: Kglossy is 0.9
     *
     * @throws Exception
     */
    @Test
    public void myFinalSphereTest_v3() throws Exception {
        Scene scene = new Scene("myFinalSphereTest_v3");
        scene.setDistance(5500);
        scene.setAmbientLight(new AmbientLight(new Color(10,0,10), 0.5));

        Sphere sphere = new Sphere(200, new Point3D(-400, 0, -1200), new Color(0, 0, 50),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // purple left

        sphere = new Sphere(100, new Point3D(100, -100, -1000), new Color(), new Material(0.1, 5, 29, 1, 0.5, 1, 0));
        scene.addGeometry(sphere); // clearly center
        scene.addLight(new PointLight(new Point3D(100, -150, -1000), 2, 0.000001, 0.0000005, new Color(180, 180, 180))); //light in sphere

        sphere = new Sphere(150, new Point3D(600, -50, -1200), new Color(0, 0, 0),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // black left

        sphere = new Sphere(150, new Point3D(-100, -50, -1500), new Color(65, 0, 0),
                new Material(0.1, 5, 29, 0.7, 0.1, 1, 0));
        scene.addGeometry(sphere); // red center

        sphere = new Sphere(300, new Point3D(500, 100, -2000), new Color(95, 70, 0),
                new Material(0.1, 5, 29, 0.6, 0.4, 1, 0));
        scene.addGeometry(sphere); // yellow big

        scene.addLight(new PointLight(new Point3D(500, 100, -2000), 2, 0.000001, 0.0000005, new Color(10, 10, 10))); //light in sphere


        Plane plane = new Plane(new Point3D(0, -200, 0), new Vector(0, 1, 0), new Color(0, 0, 0),
                new Material(3, 4, 19, 0.8, 0, 0.9, 0));
        scene.addGeometry(plane); // floor


        scene.addLight(new SpotLight(new Point3D(-1000, 2000, -3000), 1, 0.00001, 0.0000005, new Color(150, 150, 250),
                new Vector(0.5, 2, 1)));

        scene.setCamera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));
        ImageWriter imageWriter = new ImageWriter("myFinalSphereTest_v3", 800, 800, 800, 800);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * build scene presenting a space scene
     * insert balls on mars
     * the sun from the corner looking on the mars (directionalLight)
     * insert pointLight into the white balls
     * insert spotLight n on all over the scene
     * a few stars on the backgroung (with loop help)
     * after improve: Kglossy is 0.9
     *
     * @throws Exception
     */
    @Test
    public void myFinalSphereTest_mars() throws Exception {
        Scene scene = new Scene("myFinalSphereTest_mars");
        scene.setDistance(5500);
        scene.setAmbientLight(new AmbientLight(new Color(5,5,5), 0.5));
        //scene.setBackgroundColor(new Color(0,0,40));
        Sphere sphere = new Sphere(200, new Point3D(-400, 0, -1200), new Color(10, 0, 50),
                new Material(0.1, 5, 19, 1, 0.2, 1, 0));
        scene.addGeometry(sphere); // blue right

        sphere = new Sphere(100, new Point3D(100, -100, -1150), new Color(), new Material(0.1, 5, 29, 1, 0.5, 1, 0));
        scene.addGeometry(sphere); // clear center
        scene.addLight(new PointLight(new Point3D(100, -100, -1150), 2, 0.000001, 0.0000005, new Color(200, 200, 200))); //light in sphere
        scene.addLight(new PointLight(new Point3D(1000, 300, -1150), 2, 0.000001, 0.0000005, new Color(80, 80, 80))); //light behind the sphere

        sphere = new Sphere(150, new Point3D(600, -50, -1200), new Color(0, 0, 0),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // black sphere

        sphere = new Sphere(150, new Point3D(-100, -50, -1500), new Color(40, 3, 0),
                new Material(0.1, 5, 20, 1, 0.2, 1, 0));
        scene.addGeometry(sphere); // red sphere

        sphere = new Sphere(300, new Point3D(-850, 850, -2200), new Color(80, 50, 0),
                new Material(0.1, 5, 9, 1, 0.5, 1, 0));
        scene.addGeometry(sphere); // yellow sphere
        //light in the yellow sphere
        scene.addLight(new PointLight(new Point3D(-1000, 1000, -2200), 2, 0.000001, 0.0000005, new Color(120, 120, 100))); //light in sphere


        Plane plane = new Plane(new Point3D(0, -200, 0), new Vector(0, 1, 0), new Color(60, 0, 0),
                new Material(3, 4, 19, 1, 0, 0.9, 0));
        scene.addGeometry(plane); // floor
        scene.addLight(new SpotLight(new Point3D(0, 0, -5000), 1, 0.00001, 0.0000005, new Color(100, 100, 100),
                new Vector(0, 0, 1)));

        //stars
		for(int i=-8; i<8; i++)
		{
			for(int j=0; j<6; j++)
			{
				sphere = new Sphere(10, new Point3D(i*50, j*50, -1000), new Color(255,255,200), new Material(0.1, 5, 29, 1, 0.1, 1, 0));
				//scene.addGeometry(sphere);
				scene.addLight(new PointLight(new Point3D(i*50, j*50, -1000), 2, 0.000001, 0.0000005, new Color(2, 2, 1)));
			}
		}

        scene.setCamera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));
        ImageWriter imageWriter = new ImageWriter("myFinalSphereTest_mars", 800, 800, 800, 800);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * build scene like the scenes we build before
     * but now we wrapp all the scene with lightPurple ball
     * after improve: Kglossy is 0.9
     *
     * @throws Exception
     */
    @Test
    public void myFinalSphereTest_sceneIntoSphere() throws Exception {
        Scene scene = new Scene("myFinalSphereTest_sceneIntoSphere");
        scene.setDistance(5500);
        scene.setAmbientLight(new AmbientLight(new Color(10,0,10), 0.5));

        Sphere sphere = new Sphere(200, new Point3D(-400, 0, -1200), new Color(0, 0, 50),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // blue left

        sphere = new Sphere(100, new Point3D(100, -100, -1000), new Color(), new Material(0.1, 5, 29, 1, 0.5, 1, 0));
        scene.addGeometry(sphere); // black center
        scene.addLight(new PointLight(new Point3D(100, -150, -1000), 2, 0.000001, 0.0000005, new Color(180, 180, 180))); //light in sphere

         //test version 1
        sphere = new Sphere(1200, new Point3D(0, -200, -1000), new Color(), new Material(0.1, 5, 29, 1, 0.5, 1, 0));
        scene.addGeometry(sphere); // black center
        scene.addLight(new PointLight(new Point3D(0, -200, -1000), 2, 0.000001, 0.0000005, new Color(150, 150, 150))); //light in sphere


        sphere = new Sphere(150, new Point3D(600, -50, -1200), new Color(0, 0, 0),
                new Material(0.1, 5, 29, 1, 0, 1, 0));
        scene.addGeometry(sphere); // black left

        sphere = new Sphere(150, new Point3D(-100, -50, -1500), new Color(65, 0, 0),
                new Material(0.1, 5, 29, 0.7, 0.1, 1, 0));
        scene.addGeometry(sphere); // red center

        sphere = new Sphere(100, new Point3D(-250, -100, -1800), new Color(0, 50, 0),
                new Material(0.1, 5, 29, 0, 0, 1, 0));
        //scene.addGeometry(sphere); // green left

        sphere = new Sphere(300, new Point3D(500, 100, -2000), new Color(95, 70, 0),
                new Material(0.1, 5, 29, 0.6, 0.4, 1, 0));
        scene.addGeometry(sphere); // yellow big

        scene.addLight(new PointLight(new Point3D(500, 100, -2000), 2, 0.000001, 0.0000005, new Color(10, 10, 10))); //light in sphere

        Plane plane = new Plane(new Point3D(0, -200, 0), new Vector(0, 1, 0), new Color(0, 0, 0),
                new Material(3, 4, 19, 0.8, 0, 0.9, 0));
        scene.addGeometry(plane); // floor

        scene.addLight(new SpotLight(new Point3D(-1000, 2000, -3000), 1, 0.00001, 0.0000005, new Color(150, 150, 250),
                new Vector(0.5, 2, 1)));

        scene.setCamera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));
        ImageWriter imageWriter = new ImageWriter("myFinalSphereTest_sceneIntoSphere", 800, 800, 800, 800);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * build scene presenting the improve by diffuse glass
     * the triangle under the white transparent sphere
     * after improve: the Kmat is 0.4
     * @throws Exception
     */
    @Test
    public void Test_finalDiffuseGlass() throws Exception {
        Scene scene = new Scene("Test_finalDiffuseGlass");
        scene.setDistance(5500);
        scene.setAmbientLight(new AmbientLight(new Color(), 0));

        Plane plane = new Plane(new Point3D(0, 0, -510), new Vector(0, 0, 1), new Color(),
                new Material(3, 4, 19, 0, 0, 1, 0));
        scene.addGeometry(plane); // floor

        Sphere sphere = new Sphere(500, new Point3D(3000, 0, 0), new Color(50,50,50),
                new Material(2, 5, 19, 0, 1, 1, 0.4));
        scene.addGeometry(sphere);

        Triangle tr = new Triangle(new Point3D(6000, -1000, 1000), new Point3D(6000, -1000, 0), new Point3D(6000, 0, 0),
                new Color(10, 0, 50), new Material(4, 7, 20, 0, 0, 1, 0));
        scene.addGeometry(tr);

        scene.addLight(new SpotLight(new Point3D(-1000, 2000, 1000), 1, 0.00001, 0.0000005, new Color(250, 250, 250),
                new Vector(2, 2, -3)));

        scene.setCamera(new Camera(new Point3D(-10500, 0, 100), new Vector(0, 0, 1), new Vector(1, 0, 0)));
        ImageWriter imageWriter = new ImageWriter("Test_finalDiffuseGlass", 900, 900, 900, 900);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}
