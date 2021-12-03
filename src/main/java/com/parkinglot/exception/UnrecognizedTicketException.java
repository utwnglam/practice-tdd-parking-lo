package com.parkinglot.exception;

public class UnrecognizedTicketException extends RuntimeException {
  private String exceptionMessage;

  public UnrecognizedTicketException(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
  }

  public String getMessage() {
    return exceptionMessage;
  }
}
