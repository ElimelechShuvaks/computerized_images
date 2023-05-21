package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * trace the ray
     * @param ray the ray to be traced
     * @return the color
     */
    public Color traceRay(Ray ray){
        List<Point>  intersections = scene.geometries.findIntersections(ray);
        if (intersections == null)
            return scene.background;
        Point p0 = ray.findClosestPoint(intersections);
        return calcColor(p0);
    }

    /**
     * Calculates the color at a given point using the ambient light intensity of the scene.
     * @param p0 The point at which to calculate the color.
     * @return The color calculated based on the ambient light intensity of the scene.
     */
    private Color calcColor (Point p0){
        return scene.ambientLight.getIntensity();
    }
}