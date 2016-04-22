package org.earelin.ecclesia.image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Image style
 */
public class ImageStyle {
    
    private String key;
    private List<ImageProcessor> processors = new ArrayList();
    
    public ImageStyle() {}
    
    public ImageStyle(String key) {
        this.key = key;
    }

    public void addProcessor(ImageProcessor processor) {
        this.processors.add(processor);
    }
    
    public File process(File image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
}
