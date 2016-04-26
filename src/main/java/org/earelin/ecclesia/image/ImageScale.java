package org.earelin.ecclesia.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Image scaling processor
 */
public class ImageScale implements ImageProcessor {
    
    private final int width;
    private final int height;
    private final boolean upscale;

    public ImageScale(int width, int height, boolean upscale) {
        
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Width and height should be greather or equal to 0");
        }
        
        if (width == 0 && height == 0) {
            throw new IllegalArgumentException("Width or height should be greather than 0");
        }
        
        this.width = width;
        this.height = height;
        this.upscale = upscale;
    }

    @Override
    public BufferedImage process(BufferedImage inputImage) {
        final int inputImageWidth = inputImage.getWidth();
        final int inputImageHeight = inputImage.getHeight();
        
        final float inputImageAspectRatio = (float) inputImageWidth / inputImageHeight;
        
        int outputImageWidth = width;
        int outputImageHeight = height;
        
        if (outputImageWidth != 0 && outputImageHeight != 0) {
            final float outerAspectRatio = (float) outputImageWidth / outputImageHeight;
            if (inputImageAspectRatio > outerAspectRatio) {
                outputImageHeight = 0;
            } else {
                outputImageWidth = 0;
            }
        }
        
        if (!upscale && (outputImageHeight > inputImageHeight
                || outputImageWidth > inputImageWidth)) {
            return inputImage;
        }
        
        if (outputImageWidth == 0) {
            outputImageWidth = (int) Math.ceil(outputImageHeight * inputImageAspectRatio);            
        } else {
            outputImageHeight = (int) Math.ceil(outputImageWidth / inputImageAspectRatio);
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
