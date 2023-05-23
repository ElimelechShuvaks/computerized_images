package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The `Scene` class represents a collection of objects that can be rendered together in a 3D scene.
 */
public class Scene {

    /**
     * The name of this scene.
     */
    public final String name;

    /**
     * The background color of this scene.
     */
    public Color background = Color.BLACK;

    /**
     * The ambient light of this scene.
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * The geometries in this scene.
     */
    public Geometries geometries = new Geometries();

    /**
     * list of the lights in the scene
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructs a new `Scene` object with the given name.
     * The default background color is black and there is no ambient light.
     * An empty collection of geometries is created.
     *
     * @param name the name of this scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of this scene to the specified color.
     *
     * @param background the background color to set
     * @return this `Scene` object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light of this scene to the specified light.
     *
     * @param ambientLight the ambient light to set
     * @return this `Scene` object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries of this scene to the specified collection of geometries.
     *
     * @param geometries the collection of geometries to set
     * @return the Scene object
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * add a light to the scene
     * @param light the light to add
     * @return the Scene object
     */
    @SuppressWarnings("unused")
    public Scene addLight(LightSource... light) {
        lights.addAll(List.of(light));
        return this;
    }

}