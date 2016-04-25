package org.earelin.ecclesia.image;

import java.awt.image.BufferedImage;

/**
 * Image scaling processor
 */
public class ImageScale implements ImageProcessor {
    
    private final int width;
    private final int height;
    private final boolean upscale;

    public ImageScale(int width, int height, boolean upscale) {
        this.width = width;
        this.height = height;
        this.upscale = upscale;
    }

    @Override
    public BufferedImage process(BufferedImage image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
