package org.earelin.ecclesia.service.dto.resource;

import java.util.Date;

/**
 * Managed file data transfer object
 */
public class ManagedFileDto {

    private final long id;
    private final String mime;
    private final Date created;
    private final String url;

    public ManagedFileDto(long id, String mime, Date created, String url) {
        this.id = id;
        this.mime = mime;
        this.created = created;
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ManagedFileDto other = (ManagedFileDto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public String getMime() {
        return mime;
    }

    public Date getCreated() {
        return created;
    }

    public String getUrl() {
        return url;
    }
    
}
