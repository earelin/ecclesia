package org.earelin.ecclesia.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Image resizing processor
 */
public class ImageResize implements ImageProcessor {
    
    private final int width;
    private final int height;
    private final boolean upscale;

    /**
     * Constructor
     * @param width 
     * @param height
     * @param upscale
     */
    public ImageResize(int width, int height, boolean upscale) {
        
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height should be greather than 0");
        }
        
        this.width = width;
        this.height = height;
        this.upscale = upscale;
    }

    @Override
    public BufferedImage process(BufferedImage inputImage) {
        final int inputImageWidth = inputImage.getWidth();
        final int inputImageHeight = inputImage.getHeight();
        
        int outputImageWidth = width;
        int outputImageHeight = height;
        
        if (!upscale && (inputImageWidth < outputImageWidth
                || inputImageHeight < outputImageHeight)) {
            
            final float inputImageAspectRatio = (float) inputImageWidth / inputImageHeight;
            final float outputImageAspectRatio = (float) outputImageWidth / outputImageHeight;
            
            if (inputImageAspectRatio > outputImageAspectRatio) {
                outputImageWidth = (int) Math.ceil(outputImageAspectRatio * inputImageHeight);
                outputImageHeight = inputImageHeight;                
            } else {
                outputImageWidth = inputImageWidth;
                outputImageHeight = (int) Math.ceil(inputImageWidth / outputImageAspectRatio);
            }
            
        }
        
        BufferedImage outputImage
                = new BufferedImage(outputImageWidth, outputImageHeight, inputImage.getType());
        
        Graphics2D g = outputImage.createGraphics();
        ImageStyle.setGraphics2dSettings(g);
        g.drawImage(inputImage, 0, 0, outputImageWidth, outputImageHeight, null);
        g.dispose();
        
        return outputImage;
    }

}
