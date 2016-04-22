package org.earelin.ecclesia.image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Image style
 */
public class ImageStyle {
    
    private String id;
    private List<ImageProcessor> processors = new ArrayList();

    public void addProcessor(ImageProcessor processor) {
        this.processors.add(processor);
    }
    
    public File process(File image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
