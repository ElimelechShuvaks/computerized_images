package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Class Sphere is the basic class representing a sphere in the
 * Three-dimensional space.
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /** Constructor to initialize Tube based on radius and direction
     * @param radius the radius length
     * @param center the central point of the tube*/
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Getter
     *
     * @return center
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
