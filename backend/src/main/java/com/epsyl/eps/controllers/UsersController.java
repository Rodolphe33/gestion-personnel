package com.epsyl.eps.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.epsyl.eps.dtos.LoginRequestDto;
import com.epsyl.eps.dtos.RegisterRequestDto;
import com.epsyl.eps.dtos.UserResponseDto;
import com.epsyl.eps.services.authentification.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UsersController {

  private final AuthService authService;

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

  // // GET api/v1/user/id
  // @RequestMapping("/{id}")
  // public ResponseEntity<User> getUser(@PathVariable String _id) {
  //   Optional<User> user = userServices.getUserByID(_id);
  //   if (user.isPresent()) {
  //   return ResponseEntity.ok(user.get());
  //   } else {
  //     return ResponseEntity.notFound().build();
  //   }
  // }

  // // GET api/v1/user/all
  // @GetMapping("/all")
  // public Iterable<User> getUsers() {
  //   return userServices.listAll();
  // }

  // // POST api/v1/user/save
  // @PostMapping("/save")
  // public User saveUser(@RequestBody User user) {
  //   return userServices.saveOrUpdateUser(user);
  // }

  // // PUT api/v1/user/edit/id
  // @PutMapping("/edit/{id}")
  // public User updateUser(@RequestBody User user, @PathVariable String _id) {
  //   user.set_id(_id);
  //   userServices.saveOrUpdateUser(user);
  //   return user;
  // }

  // // DELETE api/v1/user/delete/id
  // @DeleteMapping("/delete/{id}")
  // public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
  //   userServices.deleteUser(userId);
  //   return ResponseEntity.noContent().build();
  // }
}
