package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Cylinder is the basic class representing a Cylinder in the
 * Three-dimensional space.
 */
public class Cylinder extends Tube {

    private double height;

    /**
     * Getter
     *
     * @return cylinder height
     */
    public double getHeight() {
        return height;
    }

    /**
     * constructor.
     *
     * @param radius
     * @param axisRay
     * @param height
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
