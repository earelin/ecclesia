package org.earelin.ecclesia.unit.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.imageio.ImageIO;
import org.earelin.ecclesia.image.ImageResize;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * ImageResize class unit test
 */
@RunWith(Parameterized.class)
public class ImageResizeTest {
    
    public static final String IMAGE_500_500
            = "src/test/resources/test-data/images/500x500.png";
    public static final String IMAGE_1000_500
            = "src/test/resources/test-data/images/1000x500.png";
    public static final String IMAGE_500_1000
            = "src/test/resources/test-data/images/500x1000.png";
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {IMAGE_500_500, 250, 250, false, 250, 250},
            {IMAGE_500_500, 750, 750, true, 750, 750},
            {IMAGE_500_500, 750, 750, false, 500, 500},
            {IMAGE_500_500, 1200, 600, false, 500, 250},
            {IMAGE_500_500, 600, 1200, false, 250, 500},
            {IMAGE_500_500, 600, 300, false, 500, 250},
            {IMAGE_500_500, 300, 600, false, 250, 500},
            
            {IMAGE_1000_500, 250, 250, false, 250, 250},
            {IMAGE_1000_500, 1200, 1200, true, 1200, 1200},
            {IMAGE_1000_500, 1500, 750, false, 1000, 500},
            {IMAGE_1000_500, 750, 1500, false, 250, 500},
            {IMAGE_1000_500, 600, 1200, false, 250, 500},
            {IMAGE_1000_500, 1500, 300, false, 1000, 200},
            {IMAGE_1000_500, 300, 1500, false, 100, 500},
            
            {IMAGE_500_1000, 250, 250, false, 250, 250},
            {IMAGE_500_1000, 1200, 1200, true, 1200, 1200},
            {IMAGE_500_1000, 750, 750, false, 500, 500},
            {IMAGE_500_1000, 1200, 600, false, 500, 250},
            {IMAGE_500_1000, 600, 1200, false, 500, 1000},
            {IMAGE_500_1000, 1200, 300, false, 500, 125},
            {IMAGE_500_1000, 300, 1200, false, 250, 1000},
        });
    }
    
    @Parameter
    public String imagePath;
    
    @Parameter(value = 1)
    public int resizerWidth;
    
    @Parameter(value = 2)
    public int resizerHeight;
    
    @Parameter(value = 3)
    public boolean resizerUpscale;
    
    @Parameter(value = 4)
    public int expectedWidth;
    
    @Parameter(value = 5)
    public int expectedHeight;
    
    @Test
    public void shouldResizeImages() throws IOException {
        final ImageResize processor = new ImageResize(resizerWidth, resizerHeight, resizerUpscale);
        
        BufferedImage resizedImage = processor.process(ImageIO.read(new File(imagePath)));
        
        assertEquals("Expected width", expectedWidth, resizedImage.getWidth());
        assertEquals("Expected height", expectedHeight, resizedImage.getHeight());
    }

}

