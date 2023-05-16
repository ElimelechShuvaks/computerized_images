package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * claas for Ambient Light
 */
public class AmbientLight {
    private final Color intensity;

    /**
     * Ambient lighting with black color
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * constractor for AmbientLight claas
     *
     * @param iA the color
     * @param kA the attenuation coefficient
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * constractor for AmbientLight claas
     *
     * @param iA the color
     * @param kA the attenuation coefficient
     */
    public AmbientLight(Color iA, double kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * getter
     *
     * @return the color intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}