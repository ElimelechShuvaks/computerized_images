package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.LinkedList;
import java.util.List;
import static primitives.Util.isZero;

public class BlackBoard {
    /**
     * this function return a beam of rays in the pixel by DOF
     * @param ray  - center point of the circular surface.
     * @param vUp     - upper vector of circular surface.
     * @param vRight  - right vector of circular surface.
     * @param radius  - radius of circular surface. (mostly aperture)
     * @param numRays - number of rays we create in the circular surface.
     * @param dist    - distance between the view plane and the focal plane
     * @return list of rays from the area of the aperture to the focal point
     */
    public List<Ray> raysInGrid(Ray ray, Vector vUp, Vector vRight, double radius, int numRays, double dist) {
        List<Ray> rays = new LinkedList<>();

        rays.add(ray); // including the original ray
        if (radius == 0)
            return rays;

        Point focalPoint = ray.getPoint(dist);
        int sqrtRays = (int) Math.floor(Math.sqrt(numRays));

        for (int i = 0; i < sqrtRays; ++i) {
            for (int j = 0; j < sqrtRays; ++j) {
                // use the radius to move the point in the pixel
                double x_move = i * radius / sqrtRays;
                double y_move = j * radius / sqrtRays;
                // define a new starting point for the new ray
                Point newP0 = ray.getP0();
                if (!isZero(x_move)) {
                    newP0 = newP0.add(vRight.scale(x_move));
                }
                if (!isZero(y_move)) {
                    newP0 = newP0.add(vUp.scale(y_move));
                }
                rays.add(new Ray(newP0, (focalPoint.subtract(newP0))));
            }
        }
        return rays;
    }
}
