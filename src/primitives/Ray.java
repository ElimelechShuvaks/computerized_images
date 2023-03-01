package primitives;

import java.util.Objects;

/**
 * Ray - half straight
 */
public class Ray {

    /**
     * Start of the ray
     */
    private final Point p0;
    /**
     * the direction of the ray
     */
    private final Vector dir;

    /**
     * constratot with tow parameters, point and vector.
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v) {
        this.p0 = p;
        this.dir = v.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Ray other))
            return false;
        return Objects.equals(dir, other.dir) && Objects.equals(p0, other.p0);    }

    @Override
    public String toString() {
        return "Ray - " + p0.toString() + " + " + dir.toString();
    }
}
