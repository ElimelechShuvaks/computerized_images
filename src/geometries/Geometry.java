package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This interface will serve as the basis for all the geometric shapes
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;

    /**
     * Function will return the color of the geometry
     * @return the color of the geometry
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Function will set the color of the geometry
     * @param emission - is a color
     * @return the Geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }


    /**
     * take a point and return the normal vector in this point
     *
     * @param p point
     * @return a normal vector
     */
    public abstract Vector getNormal(Point p);
}
