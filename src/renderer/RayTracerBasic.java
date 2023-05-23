package renderer;

import geometries.Intersectable.GeoPoint;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections)/*, ray*/);
    }

    /**
     * function calculates color of point
     *
     * @param gp point to color
     * @return color
     */
    private Color calcColor(GeoPoint gp) {
        return scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission());
    }

}