package com.epsyl.eps.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.epsyl.eps.entities.RoleEntity;
import com.epsyl.eps.entities.UserEntity;
import com.epsyl.eps.enums.ERole;
import com.epsyl.eps.playload.request.LoginRequest;
import com.epsyl.eps.playload.request.SignupRequest;
import com.epsyl.eps.playload.response.MessageResponse;
import com.epsyl.eps.playload.response.UserInfoResponse;
import com.epsyl.eps.repositories.RoleRepository;
import com.epsyl.eps.repositories.UserRepository;
import com.epsyl.eps.security.jwt.JwtUtils;
import com.epsyl.eps.security.services.UserDetailsImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponse(userDetails.getId(),
                                   userDetails.getUsername(),
                                   userDetails.getEmail(),
                                   roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.findByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    UserEntity user = new UserEntity(
      signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword())
    );

    Set<String> strRoles = signUpRequest.getRoles();
    Set<RoleEntity> roles = new HashSet<>();

    if (roles == null) {
      RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: RoleEntity is not found."));
      roles.setName(userRole);
    } else {
        switch (roles) {
        case "super_admin":
          RoleEntity superAdminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: RoleEntity is not found."));
          roles.setName(superAdminRole);
          break;
        case "admin":
          RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: RoleEntity is not found."));
          roles.setName(adminRole);
          break;
        case "rh":
          RoleEntity rhRole = roleRepository.findByName(ERole.ROLE_RH)
              .orElseThrow(() -> new RuntimeException("Error: RoleEntity is not found."));
          roles.setName(rhRole);
          break;
          case "bum":
          RoleEntity bumRole = roleRepository.findByName(ERole.ROLE_BUM)
              .orElseThrow(() -> new RuntimeException("Error: RoleEntity is not found."));
          role.setName(bumRole);
          break;
        default:
          RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: RoleEntity is not found."));
          role.setName(userRole);
        }
    }

    user.setRole(role);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
