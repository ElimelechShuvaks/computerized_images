package primitives;

/**
 * Class for representing a point in space
 */
public class Point {
    /**
     * An instance of Double3 that contains the coordinate values
     */
    final Double3 xyz;

    /**
     * Constructor with 3 parameters.
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructor with 1 parameter.
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Vector subtraction - receives a second point, returns a vector from the second point to the point on which the subtraction is performed
     * the action
     *
     * @param p second point
     * @return returns a vector
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * Adding a vector to a point – returns a new point
     *
     * @param vector to addition
     * @return returns a new point
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * distance between two points
     *
     * @param p second point
     * @return the distance in double
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    /**
     * distance between two points in square
     *
     * @param p second point
     * @return the distance in square
     */
    public double distanceSquared(Point p) {
        return (xyz.d1 - p.xyz.d1) * (xyz.d1 - p.xyz.d1) + (xyz.d2 - p.xyz.d2) * (xyz.d2 - p.xyz.d2) + (xyz.d3 - p.xyz.d3) * (xyz.d3 - p.xyz.d3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj instanceof Point p))
            return xyz.equals(p.xyz);
        return false;
    }

    @Override
    public String toString() {
        return xyz.toString();
    }
}
