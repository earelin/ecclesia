package org.earelin.ecclesia.service.dto.resouce;

/**
 * ManagedImageDTO
 */
public class ManagedImageDTO extends ManagedFileDTO {

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
