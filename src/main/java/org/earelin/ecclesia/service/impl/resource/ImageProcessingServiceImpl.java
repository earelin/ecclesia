package org.earelin.ecclesia.service.impl.resource;

import org.earelin.ecclesia.service.resource.ImageProcessingService;
import java.util.HashMap;
import java.util.Map;
import org.earelin.ecclesia.image.ImageScale;
import org.earelin.ecclesia.image.ImageScaleAndCrop;
import org.earelin.ecclesia.image.ImageStyle;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Image processing service
 */
@Service
public class ImageProcessingServiceImpl implements ImageProcessingService {
    
    public static final String THUMBNAIL_STYLE = "thumbnail";    
    public static final String MEDIUM_STYLE = "medium";
    public static final String LARGE_STYLE = "large";
    public static final String RESPONSIVE_SCALED_STYLE = "responsive-scaled";
            
    private final Map<String, ImageStyle> imageStyles;
    
    public ImageProcessingServiceImpl() {
        this.imageStyles = generateImageStyles();
    }
    
    @Async
    @Override
    public void processImage(String uri) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Predefined image styles generation
     * @return 
     */
    private Map<String, ImageStyle> generateImageStyles() {
        Map<String, ImageStyle> imageStyles = new HashMap();
        
        ImageStyle thumbnailStyle = new ImageStyle(THUMBNAIL_STYLE);
        thumbnailStyle.addProcessor(new ImageScaleAndCrop(150, 150, false));
        imageStyles.put(THUMBNAIL_STYLE, thumbnailStyle);                
        
        ImageStyle mediumStyle = new ImageStyle(MEDIUM_STYLE);
        mediumStyle.addProcessor(new ImageScale(490, 0, false));
        imageStyles.put(MEDIUM_STYLE, mediumStyle);
        
        ImageStyle largeStyle = new ImageStyle(LARGE_STYLE);
        largeStyle.addProcessor(new ImageScale(1280, 0, false));
        imageStyles.put(LARGE_STYLE, largeStyle);
        
        ImageStyle responsiveScaledStyle = new ImageStyle(RESPONSIVE_SCALED_STYLE);
        responsiveScaledStyle.addProcessor(new ImageScale(980, 0, false));
        imageStyles.put(THUMBNAIL_STYLE, responsiveScaledStyle);
        
        return imageStyles;
    }
    
}
