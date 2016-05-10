package org.earelin.ecclesia.domain.resource;

import java.net.URI;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * Managed file domain object
 *
 * Used to reference a file from another domain objects 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="Files")
public class ManagedFile {
    
    @Id
    @GeneratedValue
    private long id;
    private String mime;
    private Date created;
    @Type(type="org.earelin.ecclesia.repository.resource.PersistentUri")
    private URI uri;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final ManagedFile other = (ManagedFile) obj;
        return this.id == other.id;
    }

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

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
