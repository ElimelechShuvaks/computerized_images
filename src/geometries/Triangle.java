package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a triangular object.
 */
public class Triangle extends Polygon {

    /**
     * Constructor to initialize Triangle based on three points
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = this.plane.findIntersections(ray);
        if (result == null) {
            return null;
        }

        Point p0 = this.vertices.get(0);
        Point p1 = this.vertices.get(1);
        Point p2 = this.vertices.get(2);
        Point p = result.get(0);

        try {
            Vector n1 = p1.subtract(p0).crossProduct(p0.subtract(p));
            Vector n2 = p2.subtract(p1).crossProduct(p1.subtract(p));
            Vector n3 = p0.subtract(p2).crossProduct(p2.subtract(p));

            if ((n1.dotProduct(n2) > 0 && n2.dotProduct(n3) > 0 && n3.dotProduct(n1) > 0) ||
                    (n1.dotProduct(n2) < 0 && n2.dotProduct(n3) < 0 && n3.dotProduct(n1) < 0)) {
                return result;
            }
        } catch (IllegalArgumentException e) {

        }
        return null;
    }
}
