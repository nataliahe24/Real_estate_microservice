package com.powerup.realestate.location.domain.exceptions;

public class DescriptionMaxSizeExceededException extends RuntimeException {
  public DescriptionMaxSizeExceededException(String message) {
    super(message);
  }
}
