package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Tube is the basic class representing a tube in the Three-dimensional
 * space.
 */
public class Tube extends RadialGeometry {

    /**
     * the Ray of the tube.
     */
    protected Ray axisRay;

    /**
     * constructor.
     * @param radius
     * @param axisRay
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
