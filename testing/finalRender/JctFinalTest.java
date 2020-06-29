package testing.finalRender;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Quadrangle;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class JctFinalTest {

    /**
     * build scene from 4 triangles and quadrangle
     * representing some lights that ligting the logo of JCT hanging on grey screen (quadrangle)
     *
     * @throws Exception
     */
    @Test
        public void jctFinalTest_v1() throws Exception {
            Scene scene = new Scene("jctFinalTest_v1");
            scene.setDistance(5500);
            scene.setAmbientLight(new AmbientLight(new Color(50,50,50), 0.5));
            scene.setBackgroundColor(new Color(255,255,255));//white

            //triangles אthat compose the JCT logo
            Triangle triangle= new Triangle(new Point3D(0, 150,-3000),new Point3D(300, 350,-3000),new Point3D(300, 0,-3000),new Color(50,100,160),new Material(0,5,29,0,0,1,0));
            scene.addGeometry(triangle);//purple right up
            triangle= new Triangle(new Point3D(0, 150,-3000),new Point3D(-300, 350,-3000),new Point3D(-300, 0,-3000),new Color(70,10,70),new Material(0,5,29,0,0,1,0));
            scene.addGeometry(triangle);//purple right down
            triangle= new Triangle(new Point3D(0, 150,-3000),new Point3D(0, -200,-3000),new Point3D(300, 0,-3000),new Color(39,114,155),new Material(0,5,29,0,0,1,0));
            scene.addGeometry(triangle);//blue left up
            triangle= new Triangle(new Point3D(0, 150,-3000),new Point3D(0, -200,-3000),new Point3D(-300, 0,-3000),new Color(40,5,60),new Material(0,5,29,0,0,1,0));
            scene.addGeometry(triangle);//green left down

            Quadrangle quadrangle = new Quadrangle(new Point3D(-600, -600,-3100),new Point3D(-600, 600,-3100),new Point3D(600, -600,-3100),new Point3D(600, 600,-3100),new Color(200,200,200),new Material(0,5,29,1,0,1,0));
            scene.addGeometry(quadrangle);

            scene.addLight(new SpotLight(new Point3D(60, 100, -2500), 1, 0.00001, 0.0000005, new Color(18, 18, 18),
                    new Vector(-1, -1, 0)));
            scene.addLight(new SpotLight(new Point3D(-230, 0, -2500), 1, 0.00001, 0.0000005, new Color(15, 15, 15),
                    new Vector(-1, -1, 0)));
            scene.addLight(new SpotLight(new Point3D(-500, 1000, 0), 1, 0.00001, 0.0000005, new Color(120, 120, 100),
                    new Vector(-1, -1, 0)));
            scene.addLight(new PointLight(new Point3D(-400, 500, -400), 2, 0.000001, 0.0000005, new Color(20, 20, 20)));


            scene.setCamera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));
            ImageWriter imageWriter = new ImageWriter("jctFinalTest_v1", 800, 800, 800, 800);
            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();
        }
}
