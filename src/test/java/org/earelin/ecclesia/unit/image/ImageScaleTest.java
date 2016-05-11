package org.earelin.ecclesia.unit.image;

import org.earelin.ecclesia.image.ImageScale;
import org.junit.Test;

/**
 * ImageScale class unit test
 */
public class ImageScaleTest {

    @Test(expected = IllegalArgumentException.class)
    public void widthAndHeightShouldNotBeBothZero() {
        new ImageScale(0, 0, true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void widthShouldNotBeLessThanZero() {
        new ImageScale(-10, 200, true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void heightShouldNotBeLessThanZero() {
        new ImageScale(200, -10, true);
    }
    
}
