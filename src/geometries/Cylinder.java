package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Cylinder is the basic class representing a Cylinder in the
 * Three-dimensional space.
 */
public class Cylinder extends Tube {

    private final double height;

    /**
     * Getter
     *
     * @return cylinder height
     */
    @SuppressWarnings("unused")
    public double getHeight() {
        return height;
    }

    /**
     * Constructor to initialize Cylinder based on radius, the center axis and height
     *
     * @param radius  first number value
     * @param axisRay second number value
     * @param height  third number value
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
