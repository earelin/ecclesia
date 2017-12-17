package org.earelin.ecclesia.service.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.earelin.ecclesia.image.ImageScale;
import org.earelin.ecclesia.image.ImageScaleAndCrop;
import org.earelin.ecclesia.image.ImageStyle;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public static final String GENERATED_IMAGES_PATH = "/styled-images";
            
    private final Map<String, ImageStyle> imageStyles;
    
    private final FileService fileService;
    
    @Autowired
    public ImageProcessingServiceImpl(FileService fileService) {
        this.fileService = fileService;
        this.imageStyles = generateImageStyles();
    }
    
    @Async
    @Override
    public void processImage(URI uri) throws Exception {
        File image = fileService.get(uri);
        final BufferedImage bufferedImage = ImageIO.read(image);
        
        for (ImageStyle imageStyle : imageStyles.values()) {
            BufferedImage styledImage = imageStyle.process(bufferedImage);
            File imageFile = fileService.create(buildGeneratedImagePath(imageStyle, uri));
            ImageIO.write(styledImage, FilenameUtils.getExtension(image.getName()), imageFile);
        }
    }
    
    @Override
    public Map<String, URI> getGeneratedImagesPaths(URI uri) throws URISyntaxException {
        Map<String, URI> generatedImages = new HashMap<>();
        
        for (ImageStyle imageStyle : imageStyles.values()) {
            generatedImages.put(imageStyle.getKey(),
                    buildGeneratedImagePath(imageStyle, uri));
        }
        
        return generatedImages;
    }

    @Override
    public void deleteGeneratedImages(URI uri) throws Exception {        
        for (ImageStyle imageStyle : imageStyles.values()) {
            fileService.delete(buildGeneratedImagePath(imageStyle, uri));
        }
    }
    
    @Override
    public Map<String, URL> getGeneratedImagesUrls(URI uri) throws Exception {
        Map<String, URL> generatedImages = new HashMap<>();
        
        for (ImageStyle imageStyle : imageStyles.values()) {
            generatedImages.put(imageStyle.getKey(),
                fileService.getUrl(buildGeneratedImagePath(imageStyle, uri)));
        }
        
        return generatedImages;
    }
    
    private URI buildGeneratedImagePath(ImageStyle imageStyle, URI uri) throws URISyntaxException {
        return new URI(uri.getScheme() + "://" + GENERATED_IMAGES_PATH + "/" + imageStyle.getKey() + uri.getPath());
    }
    
    /**
     * Predefined image styles generation
     * @return 
     */
    private Map<String, ImageStyle> generateImageStyles() {
        Map<String, ImageStyle> imageStyles = new HashMap<>();
        
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
        imageStyles.put(RESPONSIVE_SCALED_STYLE, responsiveScaledStyle);
        
        return imageStyles;
    }

}
