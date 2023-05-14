package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * class for legend of geometric bodies
 */
public class Geometries implements Intersectable {
    private final List<Intersectable> geometries;

    /**
     * Default constructor that initializes an empty linked list to hold the geometric objects
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * Constructor that takes a variable number of Intersectable objects and adds them to the list
     *
     * @param geometries - geometries to insert
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        add(geometries);
    }

    /**
     * Method to add a variable number of Intersectable objects to the list
     *
     * @param geometries- to add
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries)
            this.geometries.add(geometry);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        List<Point> temp;
        for (Intersectable geometry : geometries) {
            temp = geometry.findIntersections(ray);
            if (temp != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(temp);
            }
        }

        return intersections;
    }
}