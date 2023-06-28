package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a camera object
 */
public class Camera {

    //************* fields ****************
    private final Point position;
    private final Vector vTo;
    private final Vector vUp;
    private final Vector vRight;
    private double distance;
    private double height;
    private double width;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    boolean isMultyThreding = false;
    int threadsCount = 1;
    private PixelManager pixelManager;

    // --------- fields DOF -------
    private boolean isDepthOfField = false;
    private double focalDistance = 0;
    private int numOfRays = 1;
    private double apertureSize = 1;

    //************* constructor ****************

    /**
     * constructor that build the camera
     *
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

    //************* getters ****************

    /**
     * function that gets the position of the camera
     *
     * @return the position
     */
    @SuppressWarnings("unused")
    public Point getPosition() {
        return position;
    }

    /**
     * function that gets the vTo vector
     *
     * @return the vTo vector
     */
    @SuppressWarnings("unused")
    public Vector getVTo() {
        return vTo;
    }

    /**
     * function that gets the vUp vector
     *
     * @return the vUp vector
     */
    @SuppressWarnings("unused")
    public Vector getVUp() {
        return vUp;
    }

    /**
     * function that gets the vRight vector
     *
     * @return the vRight vector
     */
    @SuppressWarnings("unused")
    public Vector getVRight() {
        return vRight;
    }

    /**
     * function that gets the distance
     *
     * @return the distance
     */
    @SuppressWarnings("unused")
    public double getDistance() {
        return distance;
    }

    /**
     * function that gets the height
     *
     * @return the height
     */
    @SuppressWarnings("unused")
    public double getHeight() {
        return height;
    }

    /**
     * function that gets the width
     *
     * @return the width
     */
    @SuppressWarnings("unused")
    public double getWidth() {
        return width;
    }

    //************* setters ****************

    /**
     * Sets the image writer for the camera.
     *
     * @param imageWriter the image writer
     * @return the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the ray tracer for the camera.
     *
     * @param rayTracer the ray tracer
     * @return the camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * function that sets the width and height
     *
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
     *
     * @param distance distance from the Camera to the View Plane
     * @return the camera
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * setter for Multy Threding
     *
     * @param isMultyThreding boolean for on/off the MultyThreding
     * @param threadsCount    count of threds
     * @return the camera
     */
    public Camera setMultyThreding(boolean isMultyThreding, int threadsCount) {
        this.isMultyThreding = isMultyThreding;
        this.threadsCount = threadsCount;
        return this;
    }

    /**
     * @param isDepthOfField isDepthOfField on/off
     * @param focalDistance distance of the focus
     * @param apertureSize the aperture size
     * @param numOfRays the num of rays to create
     * @return the camera
     */
    public Camera setDepthOfFiled(boolean isDepthOfField, double focalDistance, double apertureSize, int numOfRays) {
        this.isDepthOfField = isDepthOfField;
        this.focalDistance = focalDistance;
        this.apertureSize = apertureSize;
        this.numOfRays = numOfRays;
        return this;
    }

    /**
     * function that gets the ray from the camera to the point
     *
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
        Point rayEndPoint = pC;

        if (!isZero(yI))
            rayEndPoint = rayEndPoint.add(vUp.scale(yI));
        if (!isZero(xJ))
            rayEndPoint = rayEndPoint.add(vRight.scale(xJ));

        return new Ray(position, rayEndPoint.subtract(position));
    }

    /**
     * loop over all pixels on vp
     * construct ray
     * find intersections
     * calculate the color
     * and write to image
     */
    public Camera renderImage() {
        if (position == null || vTo == null || vUp == null || vRight == null || distance == 0 || height == 0 || width == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        int nY = this.imageWriter.getNy();
        int nX = this.imageWriter.getNx();
        double printInterval = 0.1;

        this.pixelManager = new PixelManager(nY, nX, printInterval);

        if (threadsCount > 0) {
            List<Thread> threads = new LinkedList<>();
            while (threadsCount-- > 0) {
                threads.add(new Thread(() -> {
                    PixelManager.Pixel pixel;
                    while ((pixel = pixelManager.nextPixel()) != null)
                        castRay(nX, nY, pixel.col(), pixel.row());
                }));
            }

            // Wait for all threads to finish
            for (Thread thread : threads) thread.start();
            for (Thread thread : threads)
                try {
                    thread.join();
                } catch (InterruptedException ignored) {
                }
        } else
            for (int i = 0; i < nX; i++)
                for (int j = 0; j < nY; j++) this.castRay(nX, nY, j, i);

        return this;
    }

    /**
     * Prints a grid on the image writer by writing pixels of the specified color
     * at regular intervals.
     *
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
     *
     * @throws MissingResourceException if the image writer is not initialized
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
        imageWriter.writeToImage();
    }

    /**
     * function that casts ray and returns color
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param j  the x coordinate
     * @param i  the y coordinate
     */
    private void castRay(int nX, int nY, int j, int i) {
        Color color = Color.BLACK;
        if (!isDepthOfField)
            color = rayTracer.traceRay(constructRay(nX, nY, j, i));
        else {
            List<Ray> rays = constructRaysDof(nX, nY, j, i);
            for (Ray ray : rays)
                color = color.add(this.rayTracer.traceRay(ray));
            color = color.reduce(rays.size());

        }
        imageWriter.writePixel(j, i, color);
        pixelManager.pixelDone();
    }

    /**
     * this function gets the view plane size and a selected pixel,
     * and return the rays from the view plane which intersects the focal plane
     *
     * @param nX - amount of columns in view plane (number of pixels)
     * @param nY - amount of rows in view plane (number of pixels)
     * @param j  - X's index
     * @param i  - Y's index
     * @return - the list of rays which goes from the pixel through the focal plane
     */
    public List<Ray> constructRaysDof(int nX, int nY, int j, int i) {
        BlackBoard blackBoard = new BlackBoard();
        // the returned list of rays
        List<Ray> rays = new LinkedList<>();
        // add the center ray to the list
        Ray centerRay = constructRay(nX, nY, j, i);
        // calculate the actual size of a pixel
        // pixel height is the division of the view plane height in the number of rows of pixels
        double pixelHeight = alignZero(height / nY); // Ry = h/Ny
        // pixel width is the division of the view plane width in the number of columns of pixels
        double pixelWidth = alignZero(width / nX); // Rx = w/Nx
        if (numOfRays > 1) {
            // apertureSize is the value of how many pixels it spreads on
            double apertureRadius = Math.sqrt(apertureSize * (pixelHeight * pixelWidth)) / 2d;
            rays.addAll(blackBoard.raysInGrid(centerRay, vUp, vRight, apertureRadius, numOfRays, focalDistance));
        } else
            rays.add(centerRay);

        return rays;
    }
}