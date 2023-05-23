package lighting;

import primitives.*;

/**
 * DirectionalLight class
 */
public class DirectionalLight extends Light implements LightSource {

    private final Vector direction;

    /**
     * Constructor that sets the light's intensity.
     * @param intensity the light's intensity.
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        return intensity;
    }

    @Override
    public Vector getL(Point point) {
        return direction;
    }
}