package com.epsyl.eps.dtos;

import java.util.Collection;

import com.epsyl.eps.entities.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
  private String _id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Collection<Role> roles;
  private String message;

  public UserResponseDto(String _id, String firstName, String lastName, String email, String password,
      Collection<Role> roles, String message) {
    this._id = _id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password =  password;
    this.roles = roles;
    this.message = message;
  }
}