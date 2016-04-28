package org.earelin.ecclesia.service.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;


/**
 * Image processing service
 */
public interface ImageProcessingService {

    /**
     * Process an image applying image styles
     * @param uri 
     */
    void processImage(URI uri) throws Exception;
    
    /**
     * Returns the generated derived images
     * @param uri
     * @return
     */
    Map<String, URI> getGeneratedImagesPaths(URI uri) throws URISyntaxException;
    
    void deleteGeneratedImages(URI uri) throws Exception;

    public Map<String, URL> getGeneratedImagesUrls(URI fileUri) throws Exception;
}
