package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

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
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     * constructor that build the camera
     * @param position the position
     * @param vTo      the vTo vector
     * @param vUp      the vUp vector
     * @throws IllegalArgumentException if vTo and vUp are not orthogonal
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
     * Sets the image writer for the camera.
     * @param imageWriter the image writer
     * @return the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the ray tracer for the camera.
     * @param rayTracer the ray tracer
     * @return the camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
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

        double yI = -(i - (nY - 1d) / 2) * rY;
        double xJ = (j - (nX - 1d) / 2) * rX;
        Point rayEndPoint  = pC;

        if (!isZero(yI))
            rayEndPoint  = rayEndPoint .add(vUp.scale(yI));
        if (!isZero(xJ))
            rayEndPoint  = rayEndPoint .add(vRight.scale(xJ));

        return new Ray(position, rayEndPoint .subtract(position));
    }

    /**
     * Renders the image by casting rays from the camera to the view plane and
     * storing the resulting colors in the image writer.
     * @throws MissingResourceException if the camera or image writer is not initialized
     */
    public Camera renderImage(){
        if(position == null || vTo == null || vUp == null || vRight == null || distance == 0 || height == 0 || width == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(j, i, this.castRay(nX, nY, i, j));
            }
        }
        return this;
    }

    /**
     * Prints a grid on the image writer by writing pixels of the specified color
     * at regular intervals.
     * @param interval the interval at which to place the grid lines
     * @param color    the color of the grid lines
     * @throws MissingResourceException if the image writer is not initialized
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                if ((i % interval == 0) || (j % interval == 0))
                    imageWriter.writePixel(i, j, color);
            }
        }
    }


    /**
     * Writes the rendered image to the output file.
     * @throws MissingResourceException if the image writer is not initialized
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        imageWriter.writeToImage();
    }

    private Color castRay(int nX, int nY, int i, int j){
        Ray tempRay = constructRay(nX, nY, j, i);
        return rayTracer.traceRay(tempRay);

    }
}
