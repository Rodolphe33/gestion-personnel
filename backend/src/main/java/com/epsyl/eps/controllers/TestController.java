package com.epsyl.eps.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('RH') or hasRole('BUM') or hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
  public String userAccess() {
    return "User Content.";
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

  @GetMapping("/super_admin")
  @PreAuthorize("hasRole('SUPER_ADMIN')")
  public String superAdminAccess() {
    return "Super Admin Content.";
  }
}
