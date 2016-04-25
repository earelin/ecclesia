package org.earelin.ecclesia.service.resource;


/**
 * Image processing service
 */
public interface ImageProcessingService {

    /**
     * Process an image applying image styles
     * @param uri 
     */
    void processImage(String uri) throws Exception;
    
    void getDerivedImages(String uri);
    
}
