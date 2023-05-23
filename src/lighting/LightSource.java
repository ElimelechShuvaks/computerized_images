package lighting;

import primitives.*;

/**
 * Interface for light sources.
 */
public interface LightSource {

    /**
     * returns the color of the light source
     * @param p point to check the color
     * @return color of the light source
     */
    public Color getIntensity(Point point);

    /**
     * returns the direction of the light source
     * @param p point to check the direction
     * @return direction of the light source
     */
    public Vector getL(Point point);

}