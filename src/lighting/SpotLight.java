package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * SpotLight class
 */

public class SpotLight extends PointLight {

    private final Vector direction;

    /**
     * Constructor that sets the light's intensity.
     * @param intensity the light's intensity.
     * @param position the position of the point light
     * @param direction the direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        double dirL = alignZero(direction.dotProduct(getL(point)));
        return dirL <= 0 ? Color.BLACK : super.getIntensity(point).scale(dirL);
    }
}