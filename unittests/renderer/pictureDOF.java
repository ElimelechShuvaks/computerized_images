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
public class pictureDOF {
    private final Scene scene = new Scene("Test scene");

    /**
     * build a big biutilule picture
     */
    @Test
    public void DOFpicture() {
        Color[] colors = new Color[] {new Color(yellow), new Color(red), new Color(green)};

        Camera camera = new Camera(new Point(-300, 0, 20), new Vector(1, 0, 0), new Vector(0, 0, 1))
                .setVPSize(250, 250).setVPDistance(500);

        scene.setAmbientLight(new AmbientLight(new Color(white).reduce(6), new Double3(0.15)));

        scene.geometries.add(
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(black).reduce(1.3))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100).setKt(0).setKr(0.3)));

        for (int j = -9, w = 0; j < 5; j++, w++){
            for (int i = 0; i < 6; ++i){
                scene.geometries.add(
                        new Sphere(3.4, new Point(-14 * w, 7 * j, 4 + 6.2 * i))
                                .setEmission(colors[i % 3].reduce(10))
                                .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0.7)));
            }
        }

        scene.geometries.add(
                new Plane(new Point(10, 1, -1), new Point(10, -1, -1), new Point(10, 0, 3))
                        .setEmission(new Color(darkGray).reduce(15))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(80).setKt(0).setKr(0))
        );

        scene.lights.add(new DirectionalLight(new Color(white).scale(1.6), new Vector(1, -0.3, 0)));

        ImageWriter imageWriter = new ImageWriter("pictureDOF", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .setMultyThreding(true, 5)
                .setDepthOfFiled(true, 40, 0.5, 81)
                .renderImage() //
                .writeToImage();
    }

}