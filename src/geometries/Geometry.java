package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This interface will serve as the basis for all the geometric shapes
 */
public interface Geometry extends Intersectable {
    /**
     * take a point and return the normal vector in this point
     *
     * @param p point
     * @return a normal vector
     */
    public Vector getNormal(Point p);
}
