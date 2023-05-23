package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * represents a plane in space.
 */
public class Plane extends Geometry {

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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point rayP0 = ray.getP0();
        Vector rayDir = ray.getDir();

        double checkOrthogonal = normal.dotProduct(rayDir);
        // the ray is orthogonal to the plane or the ray starts at Q of the plane
        if (Util.isZero(checkOrthogonal) || p0.equals(rayP0)) return null;

        double numerator = p0.subtract(rayP0).dotProduct(normal);
        if (Util.isZero(numerator)) return null; // the ray is started in the plane

        double t = Util.alignZero(numerator / checkOrthogonal);
        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }

}