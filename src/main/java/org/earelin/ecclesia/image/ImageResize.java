package org.earelin.ecclesia.image;

import java.awt.image.BufferedImage;

/**
 * Image resizing processor
 */
public class ImageResize implements ImageProcessor {
    
    private int width;
    private int height;
    private boolean upscale;

    public ImageResize() {}
    
    public ImageResize(int width, int height, boolean upscale) {
        this.width = width;
        this.height = height;
        this.upscale = upscale;
    }

    @Override
    public BufferedImage process(BufferedImage image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean canUpscale() {
        return upscale;
    }

    public void setUpscale(boolean upscale) {
        this.upscale = upscale;
    }

}
