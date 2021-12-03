package com.parkinglot.exception;

public class NoAvailablePositionException extends RuntimeException {
  private String exceptionMessage;

  public NoAvailablePositionException(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
  }

  public String getMessage() {
    return exceptionMessage;
  }
}
