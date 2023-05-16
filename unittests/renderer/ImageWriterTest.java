package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;

/**
 * JUnit test class of ImageWriter class.
 */
public class ImageWriterTest {

    /**
     * Test method for {@link ImageWriter#writeToImage()}.
     */
    @Test
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("test", 800, 500);
        for (int i = 0; i < 800; i++)
            for (int j = 0; j < 500; j++) {
                if ((i % 50 == 0) || (j % 50 == 0))
                    imageWriter.writePixel(i, j, Color.BLACK);
                else imageWriter.writePixel(i, j, new Color(188, 146, 175));

            }

        imageWriter.writeToImage();
    }
}