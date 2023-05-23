package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * JUnit test class of ImageWriter class.
 */
public class ImageWriterTest {

    /**
     * Test method for {@link ImageWriter#writeToImage()}.
     */
    @Test
    void writeToImage() {
        final Color color = new Color(188, 146, 175);
        final int width = 801;
        final int height = 501;
        final int step = 50;

        ImageWriter imageWriter = new ImageWriter("test", width, height);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                imageWriter.writePixel(i, j, (i % step == 0) || (j % step == 0) ? Color.BLACK : color);
        imageWriter.writeToImage();
    }
}