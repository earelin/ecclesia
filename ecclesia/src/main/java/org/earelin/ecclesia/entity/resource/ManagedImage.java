package org.earelin.ecclesia.entity.resource;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Managed images
 * Images managed by the system
 */
@Entity
@Table(name="Images")
public class ManagedImage extends ManagedFile {
    
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
