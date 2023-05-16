package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

/**
 * Represents a camera object
 */
public class Camera {
    private Point position;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double distance;
    private double height;
    private double width;

    /**
     * constructor that build the camera
     * @param position the position
     * @param vTo the vTo vector
     * @param vUp the vUp vector
     *
     */
    public Camera(Point position, Vector vTo, Vector vUp) {
        if(!Util.isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vTo and vUp must be orthogonal");
        this.position = position;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * function that gets the position of the camera
     * @return the position
     * */
    public Point getPosition() {
        return position;
    }

    /**
     * function that gets the vTo vector
     * @return the vTo vector
     * */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * function that gets the vUp vector
     * @return the vUp vector
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * function that gets the vRight vector
     * @return the vRight vector
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * function that gets the distance
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * function that gets the height
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * function that gets the width
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * function that sets the width and height
     * @param width
     * @param height
     * @return the camera
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * function that sets the distance
     * @param distance
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
     * @param i the x coordinate
     * @param j the y coordinate
     * @return the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point pC = position.add(vTo.scale(distance));

        double rY = height/nY;
        double rX = width/nX;

        double Yi = -(i - (nY - 1d) / 2) * rY;
        double Xj = (j - (nX - 1d) / 2) * rX;
        Point Pij = pC;

        if(!Util.isZero(Yi))
            Pij = Pij.add(vUp.scale(Yi));
        if(!Util.isZero(Xj))
            Pij = Pij.add(vRight.scale(Xj));

        try{
            return new Ray(position, Pij.subtract(position));
        }catch (Exception e)
        {
            throw e;
        }
    }
}
