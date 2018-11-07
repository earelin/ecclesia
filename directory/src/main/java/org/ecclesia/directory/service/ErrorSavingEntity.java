package org.ecclesia.directory.service;

public class ErrorSavingEntity extends Exception {

  private static final long serialVersionUID = 2490510393962794300L;

  public ErrorSavingEntity(String msg) {
    super(msg);
  }
}
