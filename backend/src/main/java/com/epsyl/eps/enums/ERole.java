package com.epsyl.eps.enums;

public enum ERole {
  ADMIN,
  RH,
  BUM;

  public String getRoleName() {
    return this.name().toUpperCase(); // Convertit l'enum en String majuscule
  }
}
