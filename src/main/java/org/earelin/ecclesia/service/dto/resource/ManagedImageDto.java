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
    
}
