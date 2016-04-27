package org.earelin.ecclesia.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Image scaling and cropping processor
 */
public class ImageScaleAndCrop implements ImageProcessor {

    private final int width;
    private final int height;
    private final boolean upscale;
    
    public ImageScaleAndCrop(int width, int height, boolean upscale) {
        
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
        
        if (!upscale && (width > inputImageHeight
                && height > inputImageWidth)) {
            return inputImage;
        }
        
        int outputImageWidth = width;
        int outputImageHeight = height;
        
        final float inputImageAspectRatio = (float) inputImageWidth / inputImageHeight;
        final float outputImageAspectRatio = (float) outputImageWidth / outputImageHeight;       
        
        BufferedImage croppedImage;
        
        if (inputImageAspectRatio > outputImageAspectRatio) {
            if (!upscale && (height > inputImageHeight)) {
                final int croppedImageX = (int) Math.ceil((float) (inputImageWidth - width) / 2);
                return inputImage.getSubimage(croppedImageX, 0, width, inputImageHeight);
            }
            
            final int croppedImageWidth = (int) Math.ceil(inputImageHeight * outputImageAspectRatio);
            final int croppedImageX = (int) Math.ceil((float) (inputImageWidth - croppedImageWidth) / 2);
            croppedImage = inputImage.getSubimage(croppedImageX, 0, croppedImageWidth, inputImageHeight);
            
        } else {
            if (!upscale && (width > inputImageWidth)) {
                final int croppedImageY = (int) Math.ceil((float) (inputImageHeight - height) / 2);
                return inputImage.getSubimage(0, croppedImageY, inputImageWidth, height);
            }
            
            final int croppedImageHeight = (int) Math.ceil((float) inputImageWidth / outputImageAspectRatio);
            final int croppedImageY = (int) Math.ceil((float) (inputImageHeight - croppedImageHeight) / 2);
            croppedImage = inputImage.getSubimage(0, croppedImageY, inputImageWidth, croppedImageHeight);            
        }
        
        BufferedImage outputImage
                = new BufferedImage(outputImageWidth, outputImageHeight, inputImage.getType());
        
        Graphics2D g = outputImage.createGraphics();
        ImageStyle.setGraphics2dSettings(g);
        g.drawImage(croppedImage, 0, 0, outputImageWidth, outputImageHeight, null);
        g.dispose();
        
        return outputImage;        
    }
    
}
