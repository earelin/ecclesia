package org.earelin.ecclesia.service.dto.resource;

import java.util.Date;
import java.util.Map;

/**
 *
 */
public class ManagedImageDto extends ManagedFileDto {
    
    public final Map<String, String> styles;
    
    public ManagedImageDto(long id, String mime, Date created, String url, Map<String, String> styles) {
        super(id, mime, created, url);
        this.styles = styles;
    }
    
    public String getStyledUrl(String style) {
        return styles.get(style);
    }
    
}
