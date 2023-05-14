package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * represents a plane in space.
 */
public class Plane implements Geometry {

    private final Point p0;
    private final Vector normal;

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     * Constructor to initialize Plane based on three points
     *
     * @param p0 first point on the plane
     * @param p1 second point on the plane
     * @param p2 third point on the plane
     */
    public Plane(Point p0, Point p1, Point p2) {
        this.p0 = p0;
        Vector v1 = p1.subtract(p2);
        Vector v2 = p0.subtract(p1);
        normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructor to initialize Plane based on the normal to the plane and point on the plane
     *
     * @param p point on the plane
     * @param v the normal to the plane
     */
    public Plane(Point p, Vector v) {
        p0 = p;
        normal = v.normalize();
    }

    /**
     * Getter
     *
     * @return p0(a point in the plane)
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Getter
     *
     * @return Normal plane
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point ray_P0 = ray.getP0();
        Vector ray_dir = ray.getDir();

        double checkOrthogonal = normal.dotProduct(ray_dir);
        if (Util.isZero(checkOrthogonal)) return null; // the ray is orthogonal to the plane

        if (p0.equals(ray_P0)) return null;
        double t = p0.subtract(ray_P0).dotProduct(normal);
        if (Util.isZero(t)) return null; // the ray is started in the plane

        t = Util.alignZero(p0.subtract(ray_P0).dotProduct(normal)) / (ray_dir.dotProduct(normal));
        if (t <= 0) return null; // the ray started after the plane

        return List.of(ray.getPoint(t));
    }
}