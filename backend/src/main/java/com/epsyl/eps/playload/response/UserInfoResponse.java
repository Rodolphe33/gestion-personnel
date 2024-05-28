package com.epsyl.eps.playload.response;

public class UserInfoResponse {
  private String id;
  private String lastName;
  private String email;
  private String role;

  public UserInfoResponse(String id, String lastName, String email, String role) {
    this.id = id;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getRole() {
    return role;
  }
}
