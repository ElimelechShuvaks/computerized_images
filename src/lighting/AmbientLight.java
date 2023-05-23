package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * claas for Ambient Light
 */
public class AmbientLight extends Light {

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
        super(iA.scale(kA));
    }

    /**
     * constractor for AmbientLight claas
     *
     * @param iA the color
     * @param kA the attenuation coefficient
     */
    @SuppressWarnings("unused")
    public AmbientLight(Color iA, double kA){
        super(iA.scale(kA));
    }

}