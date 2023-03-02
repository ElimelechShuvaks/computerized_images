package geometries;

public abstract class RadialGeometry implements Geometry {

    /**
     * radius.
     */
    protected double radius;

    /**
     * constructor.
     * @param radius
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
