package org.earelin.ecclesia.domain;

import java.util.Date;
import org.earelin.ecclesia.domain.resources.ManagedImage;

/**
 * Users of the system
 */
public class User {
    private String name;
    private String password;
    private String email;
    private Date created;
    private Date lastLogin;
    private Boolean bloked;  
    private ManagedImage avatar;
}
