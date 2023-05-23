package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Intersectable interface defines the intersection method for all the geometries in the scene
 */
public abstract class Intersectable {

    /**
     * helper class to connect point to Geometry
     */
    public static class GeoPoint {

        /**
         * The geometry associated with the GeoPoint.
         */
        public Geometry geometry;

        /**
         * The point associated with the GeoPoint.
         */
        public Point point;

        /**
         * constructor
         *
         * @param geometry to set
         * @param point    to set
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) obj;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry =" + geometry +
                    ", point =" + point +
                    '}';
        }
    }

    /**
     * function that returns a list of all intersections of a ray with the geometry
     *
     * @param ray the ray to check intersection with the geometry object
     * @return a list of all intersections points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * Finds the intersections between a given ray and geometries in the scene.
     * Returns a list of GeoPoints representing the intersections.
     *
     * @param ray The ray to find intersections with.
     * @return A list of GeoPoints representing the intersections between the ray and geometries.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Helper method for finding the intersections between a ray and geometries in the scene.
     * Subclasses should implement this method to provide specific intersection logic.
     *
     * @param ray The ray to find intersections with.
     * @return A list of GeoPoints representing the intersections between the ray and geometries.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
