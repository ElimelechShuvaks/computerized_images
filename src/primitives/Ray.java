package primitives;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Ray - half straight
 */
public class Ray {

    private final Point p0;
    private final Vector dir;

    /**
     * Constructor to initialize Ray base on start point and direction
     *
     * @param p the start point
     * @param v the direction
     */
    public Ray(Point p, Vector v) {
        this.p0 = p;
        this.dir = v.normalize();
    }

    /**
     * Getter
     *
     * @return starting point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Getter
     *
     * @return Direction vector
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * colculate the point on the ray that is at a distance from the head
     *
     * @param t the distance
     * @return the result point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * Calculate the closest point in list of intersections
     * @param points list of points
     * @return closest point
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty())
            return null;
        Point closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Point p : points) {
            double distance = p.distance(p0);
            if (distance < minDistance) {
                closest = p;
                minDistance = distance;
            }
        }
        return closest;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj instanceof Ray other))
            return p0.equals(other.p0) && dir.equals(other.dir);
        return false;
    }

    @Override
    public String toString() {
        return "Ray - " + p0 + " + " + dir;
    }
}
