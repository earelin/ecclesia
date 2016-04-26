package org.earelin.ecclesia.service.resource;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.earelin.ecclesia.image.ImageStyle;


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
}
