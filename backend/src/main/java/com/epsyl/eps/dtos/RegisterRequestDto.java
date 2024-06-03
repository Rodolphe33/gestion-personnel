package com.epsyl.eps.dtos;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

@Data
public class RegisterRequestDto {
  private String firstName;
  private String lastName;
  private String email;
  private Collection<String> roles = new ArrayList<>();
  private String password;
}
