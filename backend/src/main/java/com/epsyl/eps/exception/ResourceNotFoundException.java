package com.epsyl.eps.exception;

public class ResourceNotFoundException extends RuntimeException {
  
  public ResourceNotFoundException(String message) {
    super(message);
  }
}