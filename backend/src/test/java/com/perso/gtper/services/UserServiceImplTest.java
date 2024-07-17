package com.perso.gtper.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.perso.gtper.entities.Role;
import com.perso.gtper.entities.User;
import com.perso.gtper.enums.ERole;
import com.perso.gtper.repositories.RoleRepository;
import com.perso.gtper.repositories.UserRepository;
import com.perso.gtper.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private RoleRepository roleRepository;

  @InjectMocks
  private UserServiceImpl userService;

  private User user;
  private Role role;

  @BeforeEach
  public void setUp() {
    role = new Role("ROLE_USER", ERole.RH); // Assuming ERole is an enum or String
    user = User.builder()
      ._id(new ObjectId("667ae5ad6aa2bc3166214022"))
      .firstName("John")
      .lastName("Doe")
      .email("user@example.com")
      .password("password")
      .roles(Arrays.asList(role))
      .build();
  }

  @Test
  public void testAddNewRole() {
    when(roleRepository.save(any(Role.class))).thenReturn(role);

    Role savedRole = userService.addNewRole(role);

    assertThat(savedRole).isEqualTo(role);
    verify(roleRepository).save(role);
  }

  @Test
  public void testAddRoleToUser() {
    when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
    when(roleRepository.findByName(any(String.class))).thenReturn(Optional.of(role));

    userService.addRoleToUser(user, Arrays.asList(role));

    verify(userRepository).findByEmail(user.getEmail());
    verify(roleRepository).findByName(role.getName().getRoleName());
  }

  @Test
  public void testGetUserByID() {
    ObjectId userId = new ObjectId("667ae5ad6aa2bc3166214022");
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    Optional<User> foundUser = userService.getUserByID(userId);

    assertThat(foundUser).isPresent();
    assertThat(foundUser.get()).isEqualTo(user);
  }

  @Test
  public void testListAll() {
    List<User> users = Arrays.asList(user);
    when(userRepository.findAll()).thenReturn(users);

    Iterable<User> allUsers = userService.listAll();

    assertThat(allUsers).containsExactlyElementsOf(users);
  }

  @Test
  public void testDeleteUser() {
    ObjectId userId = new ObjectId("667ae5ad6aa2bc3166214017");
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    Optional<User> deletedUser = userService.deleteUser(userId);

    assertThat(deletedUser).isPresent();
    assertThat(deletedUser.get()).isEqualTo(user);
    verify(userRepository).deleteById(userId);
  }

  @Test
  public void testSaveUser() {
    when(userRepository.save(any(User.class))).thenReturn(user);

    User savedUser = userService.saveUser(user);

    assertThat(savedUser).isEqualTo(user);
    verify(userRepository).save(user);
  }
}
