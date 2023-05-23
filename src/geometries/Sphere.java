package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;

/**
 * Class Sphere is the basic class representing a sphere in the
 * Three-dimensional space.
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructor to initialize Tube based on radius and direction
     *
     * @param radius the radius length
     * @param center the central point of the tube
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Getter
     *
     * @return center
     */
    @SuppressWarnings("unused")
    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        Point rayP0 = ray.getP0();
        if (rayP0.equals(center))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));

        Vector rayDir = ray.getDir();

        Vector pointToCenter = center.subtract(rayP0);
        double tm = pointToCenter.dotProduct(rayDir);
        double distanceFromCenterSquared = pointToCenter.lengthSquared() - tm * tm;
        double thSquared = radiusSquared - distanceFromCenterSquared;
        if (alignZero(thSquared) <= 0) return null;

        double th = sqrt(thSquared); // always positive
        double secondDistance = tm + th;
        if (alignZero(secondDistance) <= 0) return null;

        double firstDistance = tm - th;
        return alignZero(firstDistance) <= 0 ? List.of(new GeoPoint(this, ray.getPoint(secondDistance))) //
                : List.of(new GeoPoint(this, ray.getPoint(firstDistance)), new GeoPoint(this, ray.getPoint(secondDistance)));
    }
}
