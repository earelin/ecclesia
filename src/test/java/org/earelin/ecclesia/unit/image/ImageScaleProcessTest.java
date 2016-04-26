package org.earelin.ecclesia.unit.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.imageio.ImageIO;
import org.earelin.ecclesia.image.ImageScale;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * ImageScale process method test
 */
@RunWith(Parameterized.class)
public class ImageScaleProcessTest {
    
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
            {IMAGE_500_500, 250, 125, false, 125, 125},
            {IMAGE_500_500, 125, 250, false, 125, 125},
            {IMAGE_500_500, 250, 0, false, 250, 250},
            {IMAGE_500_500, 0, 250, false, 250, 250},
            {IMAGE_500_500, 750, 750, true, 750, 750},
            {IMAGE_500_500, 750, 0, true, 750, 750},
            {IMAGE_500_500, 0, 750, true, 750, 750},
            {IMAGE_500_500, 750, 750, false, 500, 500},
            {IMAGE_500_500, 750, 0, false, 500, 500},
            {IMAGE_500_500, 0, 750, false, 500, 500},
            
            {IMAGE_1000_500, 250, 250, false, 250, 125},
            {IMAGE_1000_500, 500, 250, false, 500, 250},
            {IMAGE_1000_500, 250, 500, false, 250, 125},
            {IMAGE_1000_500, 250, 0, false, 250, 125},
            {IMAGE_1000_500, 0, 250, false, 500, 250},
            {IMAGE_1000_500, 1500, 1500, true, 1500, 750},
            {IMAGE_1000_500, 1500, 0, true, 1500, 750},
            {IMAGE_1000_500, 0, 1500, true, 3000, 1500},
            {IMAGE_1000_500, 1500, 1500, false, 1000, 500},
            {IMAGE_1000_500, 1500, 0, false, 1000, 500},
            {IMAGE_1000_500, 0, 1500, false, 1000, 500}, 
            
            {IMAGE_500_1000, 250, 250, false, 125, 250},
            {IMAGE_500_1000, 500, 250, false, 125, 250},
            {IMAGE_500_1000, 250, 500, false, 250, 500},
            {IMAGE_500_1000, 250, 0, false, 250, 500},
            {IMAGE_500_1000, 0, 250, false, 125, 250},
            {IMAGE_500_1000, 1500, 1500, true, 750, 1500},
            {IMAGE_500_1000, 1500, 0, true, 1500, 3000},
            {IMAGE_500_1000, 0, 1500, true, 750, 1500},
            {IMAGE_500_1000, 1500, 1500, false, 500, 1000},
            {IMAGE_500_1000, 1500, 0, false, 500, 1000},
            {IMAGE_500_1000, 0, 1500, false, 500, 1000},
        });
    }
    
    @Parameter
    public String imagePath;
    
    @Parameter(value = 1)
    public int scaleWidth;
    
    @Parameter(value = 2)
    public int scaleHeight;
    
    @Parameter(value = 3)
    public boolean scaleUpscale;
    
    @Parameter(value = 4)
    public int expectedWidth;
    
    @Parameter(value = 5)
    public int expectedHeight;
    
    @Test
    public void shouldScaleImages() throws IOException {
        final ImageScale processor = new ImageScale(scaleWidth, scaleHeight, scaleUpscale);
        
        BufferedImage scaledImage = processor.process(ImageIO.read(new File(imagePath)));
        
        assertEquals("Scaled image width", expectedWidth, scaledImage.getWidth());
        assertEquals("Scaled image height", expectedHeight, scaledImage.getHeight());
    }

}

