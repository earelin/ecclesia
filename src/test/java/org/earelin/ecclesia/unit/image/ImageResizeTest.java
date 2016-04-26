package org.earelin.ecclesia.unit.image;

import org.earelin.ecclesia.image.ImageResize;
import org.junit.Test;

/**
 * ImageResize class unit test
 */
public class ImageResizeTest {

    @Test(expected = IllegalArgumentException.class)
    public void widthShouldBeGreatherThanZero() {
        ImageResize imageScale = new ImageResize(0, 40, true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void heightShouldBeGreatherThanZero() {
        ImageResize imageScale = new ImageResize(40, 0, true);
    }
    
}
