package org.earelin.ecclesia.service.dto.resource;

import java.net.URL;
import java.util.Date;
import java.util.Map;

/**
 *
 */
public class ManagedImageDto extends ManagedFileDto {
    
    public final Map<String, URL> styles;
    
    public ManagedImageDto(long id, String mime, Date created, URL url, Map<String, URL> styles) {
        super(id, mime, created, url);
        this.styles = styles;
    }
    
    public URL getStyledUrl(String style) {
        return styles.get(style);
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
        final ManagedImageDto other = (ManagedImageDto) obj;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 74 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        return hash;
    }
    
}
