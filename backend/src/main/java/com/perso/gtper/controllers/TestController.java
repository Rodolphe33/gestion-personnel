package com.perso.gtper.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/rh")
  @PreAuthorize("hasRole('RH')")
  public String rhAccess() {
    return "Rh Content.";
  }
  
  @GetMapping("/bum")
  @PreAuthorize("hasRole('BUM')")
  public String bumAccess() {
    return "Bum Content.";
  }
  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Content.";
  }
}