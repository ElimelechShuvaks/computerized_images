package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class Tube is the basic class representing a tube in the Three-dimensional
 * space.
 */
public class Tube extends RadialGeometry {

    /**
     * the Ray of the tube.
     */
    protected final Ray axisRay;

    /**
     * Constructor to initialize Tube based on radius and direction
     *
     * @param radius  the radius length
     * @param axisRay the central axis of the Tube
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        double dis = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
        return p.subtract(axisRay.getPoint(dis)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
