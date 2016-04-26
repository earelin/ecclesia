package org.earelin.ecclesia.unit.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.imageio.ImageIO;
import org.earelin.ecclesia.image.ImageScaleAndCrop;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * ImageScaleAndCrop process method test
 */
@RunWith(Parameterized.class)
public class ImageScaleAndCropProcessTest {
    
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
            {IMAGE_500_500, 1000, 250, false, 500, 250},
            {IMAGE_500_500, 250, 1000, false, 250, 500},
            
            {IMAGE_1000_500, 250, 250, false, 250, 250},
            {IMAGE_1000_500, 1250, 1250, true, 1250, 1250},
            {IMAGE_1000_500, 750, 750, false, 750, 500},
            {IMAGE_1000_500, 1000, 500, false, 1000, 500},
            {IMAGE_1000_500, 500, 1000, false, 500, 500},
            
            {IMAGE_500_1000, 250, 250, false, 250, 250},
            {IMAGE_500_1000, 1250, 1250, true, 1250, 1250},
            {IMAGE_500_1000, 750, 750, false, 500, 750},  
            {IMAGE_500_1000, 1000, 500, false, 500, 500},
            {IMAGE_500_1000, 500, 1000, false, 500, 1000},
        });
    }
    
    @Parameter
    public String imagePath;
    
    @Parameter(value = 1)
    public int cropWidth;
    
    @Parameter(value = 2)
    public int cropHeight;
    
    @Parameter(value = 3)
    public boolean scaleAndCropUpscale;
    
    @Parameter(value = 4)
    public int expectedWidth;
    
    @Parameter(value = 5)
    public int expectedHeight;
    
    @Test
    public void shouldScaleAndCropImage() throws IOException {
        final ImageScaleAndCrop processor = new ImageScaleAndCrop(cropWidth, cropHeight, scaleAndCropUpscale);
        
        BufferedImage scaledAndCroppedImage = processor.process(ImageIO.read(new File(imagePath)));
        
        assertEquals("Expected width", expectedWidth, scaledAndCroppedImage.getWidth());
        assertEquals("Expected height", expectedHeight, scaledAndCroppedImage.getHeight());
    }

}
