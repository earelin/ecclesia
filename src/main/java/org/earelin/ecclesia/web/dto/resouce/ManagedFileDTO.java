package org.earelin.ecclesia.web.dto.resouce;

import java.util.Date;

/**
 * ManagedFileDTO
 */
public class ManagedFileDTO {

    private long id;
    private String mime;
    private Date created;
    private String uri;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
}
