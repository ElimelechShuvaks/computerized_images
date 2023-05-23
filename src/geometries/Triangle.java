package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        var result = this.plane.findGeoIntersections(ray);
        if (result == null) return null;

        Point p0 = this.vertices.get(0);
        Point p1 = this.vertices.get(1);
        Point p2 = this.vertices.get(2);
        Point p = result.get(0).point;

        try {
            Vector n1 = p1.subtract(p0).crossProduct(p0.subtract(p));
            Vector n2 = p2.subtract(p1).crossProduct(p1.subtract(p));
            double sign1 = alignZero(n1.dotProduct(n2));
            if (sign1 < 0) return null;

            Vector n3 = p0.subtract(p2).crossProduct(p2.subtract(p));
            if (sign1 * n1.dotProduct(n3) < 0) return null;

            return List.of(new GeoPoint(this, result.get(0).point));
        } catch (IllegalArgumentException ignore) {
            return null;
        }
    }

}
