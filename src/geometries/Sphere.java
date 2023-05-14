package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

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
        Point ray_P0 = ray.getP0();
        Vector ray_dir = ray.getDir();

        if (ray_P0.equals(center)) {
            List<Point> intersections = new ArrayList<Point>();
            intersections.add(center.add(ray_dir.scale(radius)));
            return intersections;
        }

        Vector pointToCenter = center.subtract(ray_P0);
        double tm = pointToCenter.dotProduct(ray_dir);
        double distanceFromCenter = sqrt(pointToCenter.dotProduct(pointToCenter) - tm * tm);
        if (distanceFromCenter >= radius) {
            return null;
        }

        double th = sqrt(radius * radius - distanceFromCenter * distanceFromCenter);
        double firstDistance = tm - th;
        double secondDistance = tm + th;
        if (firstDistance > 0 || secondDistance > 0) {
            List<Point> intersections = new ArrayList<Point>();
            if (Util.alignZero(firstDistance) > 0) {
                Point firstIntersection = ray.getPoint(firstDistance);
                intersections.add(firstIntersection);
            }
            if (Util.alignZero(secondDistance) > 0) {
                Point secondIntersection = ray.getPoint(secondDistance);
                intersections.add(secondIntersection);
            } else {
                return null;
            }
            return intersections;
        }
        return null;
    }
}
