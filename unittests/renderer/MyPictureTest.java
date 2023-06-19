package renderer;

import geometries.Plane;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;
import static java.awt.Color.*;


/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 */
public class MyPictureTest {
    private final Scene scene = new Scene("Test scene");

    /**
     * build a big biutilule picture
     */
    @Test
    public void MyPicture() {
        Camera camera = new Camera(new Point(-300, 0, 5), new Vector(1, 0, 0), new Vector(0, 0, 1))
                .setVPSize(250, 250).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(white).reduce(6), new Double3(0.15)));

        double angle = 0;
        double heigh = 0.8;

        scene.geometries.add(
                new Plane(new Point(1, 0, -0.5), new Point(0, 1, -0.5), new Point(0, 0, -0.5))
                        .setEmission(new Color(black).reduce(1.3))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100).setKt(0).setKr(0.3)));

        scene.geometries.add(
                new Sphere(3.4, new Point(0, 0, 4))
                        .setEmission(new Color(yellow).reduce(10))
                        .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7)));

        scene.geometries.add(
                new Sphere(3.4, new Point(0, 0, 10.2))
                        .setEmission(new Color(red).reduce(10))
                        .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7)));

        scene.geometries.add(
                new Sphere(3.4, new Point(0, 0, 16.4))
                        .setEmission(new Color(green).reduce(10))
                        .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7).setKr(0)));

        scene.geometries.add(
                new Sphere(3.4, new Point(0, 0, 16.4 + 6.2))
                        .setEmission(new Color(yellow).reduce(10))
                        .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7)));

        scene.geometries.add(
                new Sphere(3.4, new Point(0, 0, 16.4 + 2 * 6.2))
                        .setEmission(new Color(red).reduce(10))
                        .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7)));

        scene.geometries.add(
                new Sphere(3.4, new Point(0, 0, 16.4 + 3 * 6.2))
                        .setEmission(new Color(green).reduce(10))
                        .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7).setKr(0)));

        scene.geometries.add(
                new Plane(new Point(10, 1, -1), new Point(10, -1, -1), new Point(10, 0, 3))
                        .setEmission(new Color(darkGray).reduce(15))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0).setKr(0))
        );

        java.awt.Color[] colors = {new java.awt.Color(255, 0, 0), new java.awt.Color(255, 255 / 2, 0), new java.awt.Color(255, 255, 0),
                new java.awt.Color(0, 255, 0), new java.awt.Color(0, 255, 255 / 2), new java.awt.Color(0, 255, 255),
                new java.awt.Color(0, 0, 255), new java.awt.Color(255 / 2, 0, 255), new java.awt.Color(255, 0, 255)};

        for (int i = 0; i < 200; ++i) {
            int colorIndex = i % colors.length;//generator.nextInt(colors.length);

            scene.geometries.add(
                    new Sphere(0.5, new Point(4 * Math.cos(angle), 4 * Math.sin(angle), heigh))
                            .setEmission(new Color(colors[colorIndex]).reduce(2.2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.3)));

            angle += 3.14 / 13;
            heigh += 0.13;
        }

        scene.lights.add(new DirectionalLight(new Color(white).scale(1.6), new Vector(1, -0.3, 0)));

        ImageWriter imageWriter = new ImageWriter("MyPictureTest", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}