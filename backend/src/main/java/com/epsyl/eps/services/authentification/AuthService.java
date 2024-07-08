package com.epsyl.eps.services.authentification;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epsyl.eps.dtos.LoginRequestDto;
import com.epsyl.eps.dtos.RegisterRequestDto;
import com.epsyl.eps.dtos.UserResponseDto;
import com.epsyl.eps.entities.Role;
import com.epsyl.eps.entities.User;
import com.epsyl.eps.repositories.RoleRepository;
import com.epsyl.eps.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public ResponseEntity<UserResponseDto> register(RegisterRequestDto newUser) {

    if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
      return ResponseEntity.badRequest()
        .body(UserResponseDto.builder()
          .message("L'email renseigné existe déjà !")
          .build());
    }

    // Vérification si la collection des rôles est nulle
    if (newUser.getRoles() == null) {
      return ResponseEntity.badRequest()
        .body(UserResponseDto.builder()
          .message("La collection des rôles ne peut pas être nulle.")
          .build());
    }
    Collection<Role> roles = new ArrayList<Role>();
    for (String roleName : newUser.getRoles()) {
      roleRepository.findByName(roleName).ifPresent(roles::add);
    }

    var user = User.builder()
        .firstName(newUser.getFirstName())
        .lastName(newUser.getLastName())
        .email(newUser.getEmail())
        .password(passwordEncoder.encode(newUser.getPassword()))
        .roles(roles)
        .build();

    userRepository.save(user);

    return ResponseEntity.ok(UserResponseDto.builder()
        .message("Utilisateur enregistré avec succés !")
        .build());
  }

  public ResponseEntity<UserResponseDto> login(LoginRequestDto request) {
  
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow(null);
    if (user == null) {
      return ResponseEntity.badRequest()
        .body(UserResponseDto.builder()
          .message("Email incorrect")
          .build());
    }

    try {
    Authentication authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (Exception e) {
      return ResponseEntity.badRequest()
        .body(UserResponseDto.builder()
          .message("Mot de passe incorrect")
          .build());
    }

    var jwtToken = jwtService.generateToken(user);

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("Access-Control-Expose-Headers", "Authorization");
    responseHeaders.add("Authorization", "Bearer " + jwtToken);

    return ResponseEntity.ok()
      .headers(responseHeaders)
      .body(UserResponseDto.builder()
        ._id(user.get_id().toString())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .roles(user.getRoles())
        .message("Utilisateur authentifié avec succès")
        .build());
  }
}
