package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * RayTracerBasic class that extends the RayTracer class
 */
public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * constructor that calls super constructor
     *
     * @param scene the scene to trace through
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        var intersection = findClosestIntersection(ray);
        return intersection == null ? scene.background : calcColor(intersection, ray);
    }

    /**
     * function calculates local effects of color on point
     *
     * @param gp  geometry point to color
     * @param ray ray that intersects
     * @return color
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector vector = ray.getDir();
        Vector normal = gp.geometry.getNormal(gp.point);
        double nv = alignZero(normal.dotProduct(vector));
        if (isZero(nv))
            return color;
        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector lightVector = lightSource.getL(gp.point);
            double nl = alignZero(normal.dotProduct(lightVector));
            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, lightVector, normal);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)), lightIntensity.scale(calcSpecular(material, normal, lightVector, nl, vector)));
                }
            }
        }
        return color;
    }

    /**
     * Calculates reflected ray and refraction ray
     *
     * @param geoPoint geometry point
     * @param ray ray
     * @return the color
     */
    private Color calcGlobalEffect(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        Ray reflectedRay = constructReflectionRay(geoPoint, geoPoint.geometry.getNormal(geoPoint.point), ray.getDir());
        Ray refractedRay = constructRefractionRay(geoPoint, geoPoint.geometry.getNormal(geoPoint.point), ray.getDir());
        return calcGlobalEffect(level, color, material.kR, k, reflectedRay)
                .add(calcGlobalEffect(level, color, material.kT, k, refractedRay));
    }

    /**
     * Calculates the global effects of reflection and recursion for a given intersection point.
     *
     * @param level the recursion level
     * @param color the current color
     * @param kx the accumulation of the attenuation factors
     * @param k the attenuation factor of the current material
     * @param ray the ray being traced
     * @return the updated color after applying global effects
     */
    private Color calcGlobalEffect(int level, Color color, Double3 kx, Double3 k, Ray ray) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint reflectedPoint = findClosestIntersection(ray);
        return reflectedPoint == null ? color : color.add(calcColor(reflectedPoint, ray, level - 1, kkx).scale(kx));
    }

    /**
     * function calculates specular color
     *
     * @param material    material of geometry
     * @param normal      normal of geometry
     * @param lightVector light vector
     * @param nl          dot product of normal and light vector
     * @param dir         direction of ray
     * @return specular color
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightVector, double nl, Vector dir) {
        Vector reflectedVector = lightVector.subtract(normal.scale(2 * nl));
        double minusVR = alignZero(-dir.dotProduct(reflectedVector));
        return minusVR <= 0 ? Double3.ZERO : material.kS.scale(Math.pow(minusVR, material.nShininess));
    }

    /**
     * function calculates diffusive color
     *
     * @param material material of geometry
     * @param nl       dot product of normal and light vector
     * @return diffusive color
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(nl < 0 ? -nl : nl);
    }

    /**
     * function calculates color of point
     *
     * @param geoPoint point to color
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(
                calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE));
    }

    /**
     * function calculates color of point
     *
     * @param geoPoint point to color
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffect(geoPoint, ray, level, k));
    }

    /**
     * function will check if point is unshaded
     *
     * @param geoPoint geometry point to check
     * @param l        light vector
     * @param n        normal vector
     * @return true if unshaded
     */
    @SuppressWarnings("unused")
    private boolean unshaded(GeoPoint geoPoint, Vector l, Vector n, LightSource lightSource) {
        Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections != null) {
            double distance = lightSource.getDistance(geoPoint.point);
            for (GeoPoint intersection : intersections) {
                if (intersection.point.distance(geoPoint.point) < distance)
                    return false;
            }
        }
        return true;
    }

    /**
     * Find the closest intersection point with a ray.
     *
     * @param ray The ray to checks intersections with.
     * @return The closest intersection point with the ray.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

    /**
     * function will construct a reflection ray
     *
     * @param geoPoint geometry point to check
     * @param normal   normal vector
     * @param vector   direction of ray to point
     * @return reflection ray
     */
    private Ray constructReflectionRay(GeoPoint geoPoint, Vector normal, Vector vector) {
        Vector reflectedVector = vector.subtract(normal.scale(2 * vector.dotProduct(normal)));
        return new Ray(geoPoint.point, reflectedVector, normal);
    }

    /**
     * function will construct a refraction ray
     *
     * @param geoPoint geometry point to check
     * @param normal   normal vector
     * @param vector   direction of ray to point
     * @return refraction ray
     */
    private Ray constructRefractionRay(GeoPoint geoPoint, Vector normal, Vector vector) {
        return new Ray(geoPoint.point, vector, normal);
    }

    /**
     * function will return double that represents transparency
     *
     * @param geoPoint    geometry point to check
     * @param lightSource light source
     * @param l           light vector
     * @param n           normal vector
     * @return transparency value
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        Double3 ktr = Double3.ONE;
        if (intersections == null) return ktr;

        double distance = lightSource.getDistance(geoPoint.point);

        for (GeoPoint intersection : intersections) {
            if (distance > intersection.point.distance(geoPoint.point)) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }
}