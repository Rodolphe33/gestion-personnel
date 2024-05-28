package com.epsyl.eps.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.epsyl.eps.entities.UserEntity;
import com.epsyl.eps.services.UserServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/user")
public class UsersController {

  @Autowired
  private UserServices userServices;

  // GET api/v1/user/id
  @RequestMapping("/{id}")
  public ResponseEntity<UserEntity> getUser(@PathVariable String _id) {
    Optional<UserEntity> user = userServices.getUserByID(_id);
    if (user.isPresent()) {
    return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // GET api/v1/user/all
  @GetMapping("/all")
  public Iterable<UserEntity> getUsers() {
    return userServices.listAll();
  }

  // POST api/v1/user/save
  @PostMapping("/save")
  public UserEntity saveUser(@RequestBody UserEntity user) {
    return userServices.saveOrUpdateUser(user);
  }

  // PUT api/v1/user/edit/id
  @PutMapping("/edit/{id}")
  public UserEntity updateUser(@RequestBody UserEntity user, @PathVariable String _id) {
    user.set_id(_id);
    userServices.saveOrUpdateUser(user);
    return user;
  }

  // DELETE api/v1/user/delete/id
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
    userServices.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }
}
