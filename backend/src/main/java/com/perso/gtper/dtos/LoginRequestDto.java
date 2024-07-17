package com.perso.gtper.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
  private String email;
  private String password;
}
