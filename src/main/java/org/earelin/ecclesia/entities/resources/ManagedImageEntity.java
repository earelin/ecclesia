package org.earelin.ecclesia.entities.resources;

import javax.persistence.Entity;

/**
 * Managed images
 * Images managed by the system
 */
@Entity
public class ManagedImageEntity extends ManagedFileEntity {
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
