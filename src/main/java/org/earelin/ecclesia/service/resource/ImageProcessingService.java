package org.earelin.ecclesia.service.resource;

import java.net.URISyntaxException;
import java.util.Map;


/**
 * Image processing service
 */
public interface ImageProcessingService {

    /**
     * Process an image applying image styles
     * @param uri 
     */
    void processImage(String uri) throws Exception;
    
    /**
     * Returns the generated derived images
     * @param uri
     * @return
     */
    Map<String, String> getGeneratedImagesPaths(String uri) throws URISyntaxException;
    
    void deleteGeneratedImages(String uri) throws Exception;
}
