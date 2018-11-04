package org.ecclesia.directory.service;

/**
 * An entity referenced on a service method does not exists.
 */
public class EntityDoesNotExists extends Exception {

  private static final long serialVersionUID = -6383524566588749110L;

  public EntityDoesNotExists(String message) {
    super(message);
  }

}
