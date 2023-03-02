package primitives;

/**
 * Ray - half straight
 */
public class Ray {

    private final Point p0;
    private final Vector dir;

    /**
     * constratot with tow parameters, point and vector.
     *
     * @param p
     * @param v
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
        return "Ray - " + p0.toString() + " + " + dir.toString();
    }
}
