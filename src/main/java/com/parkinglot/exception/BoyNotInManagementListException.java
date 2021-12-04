package com.parkinglot.exception;

public class BoyNotInManagementListException extends RuntimeException {
  private final String exceptionMessage;

  public BoyNotInManagementListException(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
  }

  public String getMessage() {
    return exceptionMessage;
  }
}
