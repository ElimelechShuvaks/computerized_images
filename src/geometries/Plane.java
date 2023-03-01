package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * represents a plane in space.
 */
public class Plane implements Geometry {

    private Point p0;
    private Vector normal;

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    /**
     * Build a Plane by 3 Points.
     *
     * @param p0
     * @param p1
     * @param p2
     */
    public Plane(Point p0, Point p1, Point p2) {
        this.p0 = p0;
        normal = null;
    }

    /**
     * Build a Plane by point and normal vector.
     *
     * @param p0
     * @param v
     */
    public Plane(Point p, Vector v) {
        p0 = p;
        normal = v;
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

}
