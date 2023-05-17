package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * The `Scene` class represents a collection of objects that can be rendered together in a 3D scene.
 */
public class Scene {

    /**
     * The name of this scene.
     */
    public String name;

    /**
     * The background color of this scene.
     */
    public Color background;

    /**
     * The ambient light of this scene.
     */
    public AmbientLight ambientLight;

    /**
     * The geometries in this scene.
     */
    public Geometries geometries;

    /**
     * Constructs a new `Scene` object with the given name.
     * The default background color is black and there is no ambient light.
     * An empty collection of geometries is created.
     *
     * @param name the name of this scene
     */
    public Scene(String name) {
        this.name = name;
        this.background = Color.BLACK;
        this.ambientLight = AmbientLight.NONE;
        this.geometries = new Geometries();
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
     * @return this `Scene` object
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

}