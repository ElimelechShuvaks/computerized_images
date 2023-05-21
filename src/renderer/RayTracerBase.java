package renderer;

import primitives.*;
import scene.Scene;

/**
 * The RayTracerBase class is an abstract class representing the base of all ray tracers.
 * It contains a scene field representing the scene being traced.
 */
public abstract class RayTracerBase {

    protected Scene scene;

    /**
     * Constructs a RayTracerBase object with the given scene.
     * @param scene the scene to be traced
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces a given ray and returns the color at the intersection point.
     * @param ray the ray to be traced
     * @return the color at the intersection point
     */
    public abstract Color traceRay(Ray ray);
}