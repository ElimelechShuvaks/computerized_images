package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * Represents a camera object
 */
public class Camera {
    private final Point position;
    private final Vector vTo;
    private final Vector vUp;
    private final Vector vRight;
    private double distance;
    private double height;
    private double width;

    /**
     * constructor that build the camera
     * @param position the position
     * @param vTo      the vTo vector
     * @param vUp      the vUp vector
     */
    public Camera(Point position, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        this.position = position;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * function that gets the position of the camera
     * @return the position
     */
    @SuppressWarnings("unused")
    public Point getPosition() {
        return position;
    }

    /**
     * function that gets the vTo vector
     * @return the vTo vector
     */
    @SuppressWarnings("unused")
    public Vector getVTo() {
        return vTo;
    }

    /**
     * function that gets the vUp vector
     * @return the vUp vector
     */
    @SuppressWarnings("unused")
    public Vector getVUp() {
        return vUp;
    }

    /**
     * function that gets the vRight vector
     * @return the vRight vector
     */
    @SuppressWarnings("unused")
    public Vector getVRight() {
        return vRight;
    }

    /**
     * function that gets the distance
     * @return the distance
     */
    @SuppressWarnings("unused")
    public double getDistance() {
        return distance;
    }

    /**
     * function that gets the height
     * @return the height
     */
    @SuppressWarnings("unused")
    public double getHeight() {
        return height;
    }

    /**
     * function that gets the width
     * @return the width
     */
    @SuppressWarnings("unused")
    public double getWidth() {
        return width;
    }

    /**
     * function that sets the width and height
     * @param width  width of the View Plane
     * @param height height of the View Plane
     * @return the camera
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * function that sets the distance
     * @param distance distance from the Camera to the View Plane
     * @return the camera
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * function that gets the ray from the camera to the point
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = position.add(vTo.scale(distance));

        double rY = height / nY;
        double rX = width / nX;

        double Yi = -(i - (nY - 1d) / 2) * rY;
        double Xj = (j - (nX - 1d) / 2) * rX;
        Point rayEndPoint  = pC;

        if (!isZero(Yi))
            rayEndPoint  = rayEndPoint .add(vUp.scale(Yi));
        if (!isZero(Xj))
            rayEndPoint  = rayEndPoint .add(vRight.scale(Xj));

        return new Ray(position, rayEndPoint .subtract(position));
    }
}
