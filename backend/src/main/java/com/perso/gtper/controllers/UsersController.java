package com.perso.gtper.controllers;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perso.gtper.dtos.LoginRequestDto;
import com.perso.gtper.dtos.RegisterRequestDto;
import com.perso.gtper.dtos.UserResponseDto;
import com.perso.gtper.entities.User;
import com.perso.gtper.services.UserService;
import com.perso.gtper.services.authentification.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UsersController {

  private final AuthService authService;
  private final UserService userService;

  // POST api/v1/user/register
  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> register(@RequestBody RegisterRequestDto user) {
    return authService.register(user);
  }

  // POST api/v1/user/login
  @PostMapping("/login")
  public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto user) {
    return authService.login(user);
  }

  // GET api/v1/user/id
  @RequestMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable ObjectId id) {
    Optional<User> user = userService.getUserByID(id);
    if (user.isPresent()) {
    return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // GET api/v1/user/all
  @GetMapping("/all")
  public Iterable<User> getUsers() {
    return userService.listAll();
  }

  // POST api/v1/user/id
  @PostMapping("/save")
  public User saveUser(@RequestBody User user) {
    userService.saveUser(user);
    return user;
  }

  // PUT api/v1/user/id
  @PutMapping("/{id}")
  public User updateUser(@RequestBody User user, @PathVariable ObjectId id) {
    user.set_id(id);
    userService.saveUser(user);
    return user;
  }

  // DELETE api/v1/user/delete/id
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable ObjectId userId) {
    userService.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }
}
