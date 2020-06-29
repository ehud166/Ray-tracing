package geometries;


import primitives.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * represent class that build from 2 triangles
 * (kredit to Yohanan Haik)
 */
public class Quadrangle extends Geometry implements FlatGeometry{
    private Triangle  _tri1;
    private Triangle _tri2;

    // *************** Constructors ****************** //

    /**
     * c-tor that getting 4 point and more material and color and build quadrangle
     *
     * @throws Exception
     */
    public Quadrangle(Point3D P1, Point3D P2, Point3D P3, Point3D P4, Color color, Material material) throws Exception {
        _tri1 = new Triangle(P1, P2, P4,color,material);
        _tri2 = new Triangle(P1, P3, P4,color,material);
    }

    // ************* Getters/Setters ****************** //
    public Point3D getP1(){return _tri1.getP1();}

    public Point3D getP2(){return _tri1.getP2();}

    public Point3D getP3(){return _tri2.getP2();}

    public Point3D getP4(){return _tri1.getP3();}

    /**
     * This functions computes a normal vector into specific point
     * @param point - get normal to this point
     * @return normal vector
     * @throws Exception
     */
    public Vector getNormal(Point3D point) throws Exception
    {
        return _tri1.getNormal(point);//normal to one of triangle it is the same normal to the other
    }

    // ************* operations **************** //
    /**
     * This functions computes a all the intersections of specific ray with the Quadrangle
     * calling twice to  findIntersections from triangle
     * @param ray get ray and find her intersection
     * @return map of all intersection point
     * @throws Exception
     */
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) throws Exception
    {
        Map<Geometry, List<Point3D>> MapInter1 = new HashMap<Geometry, List<Point3D>>();
        Map<Geometry, List<Point3D>> MapInter2 = new HashMap<Geometry, List<Point3D>>();

        MapInter1= _tri1.findIntersections(ray);
        MapInter2= _tri2.findIntersections(ray);

        MapInter1.putAll(MapInter2);
        return MapInter1;
    }
}









