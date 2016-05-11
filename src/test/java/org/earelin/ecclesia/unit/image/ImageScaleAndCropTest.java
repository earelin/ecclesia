package org.earelin.ecclesia.unit.image;

import org.earelin.ecclesia.image.ImageScaleAndCrop;
import org.junit.Test;

/**
 * ImageScaleAndCrop class unit test
 */
public class ImageScaleAndCropTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void widthShouldBeGreatherThanZero() {
        new ImageScaleAndCrop(0, 40, true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void heightShouldBeGreatherThanZero() {
        new ImageScaleAndCrop(40, 0, true);
    }
    
}
