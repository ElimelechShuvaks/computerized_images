package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * class for legend of geometric bodies
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor that initializes an empty linked list to hold the geometric objects
     */
    public Geometries() {
    }

    /**
     * Constructor that takes a variable number of Intersectable objects and adds them to the list
     *
     * @param geometries - geometries to insert
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Method to add a variable number of Intersectable objects to the list
     *
     * @param geometries- to add
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : geometries) {
            var temp = geometry.findGeoIntersections(ray);
            if (temp != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(temp);
            }
        }
        return intersections;
    }

}