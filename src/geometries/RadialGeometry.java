package geometries;

/**
 * This class will serve as the basis for all the radial geometries shapes
 */
public abstract class RadialGeometry implements Geometry {

    /**
     * radius.
     */
    protected final double radius;

    /** Constructor to initialize RadialGeometry based on radius
     * @param radius the radius*/
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
