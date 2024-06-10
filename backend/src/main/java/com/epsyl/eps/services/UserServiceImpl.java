package com.epsyl.eps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epsyl.eps.entities.Role;
import com.epsyl.eps.entities.User;
import com.epsyl.eps.repositories.RoleRepository;
import com.epsyl.eps.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Cet utilisateur n'existe pas !"));
  }

  @Override
  public Role addNewRole(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(User user, List<Role> roles) {
  //   User userFromDB = userRepository.findByEmail(user.getEmail()).orElse(null);

  //   roles.stream()
  //       .map(Role::getName)
  //       .map(roleRepository::findByName)
  //       .forEach(userFromDB.getRoles()::add);
  }

  public Optional<User> getUserByID(String userId) {
    return this.userRepository.findById(userId);
  }

  public Iterable<User> listAll() {
    return this.userRepository.findAll();
  }

  public Optional<User> deleteUser(String _id) {
    return this.userRepository.findById(_id).flatMap(user -> {
      userRepository.deleteById(user.get_id());
      return Optional.of(user);
    });
  }

  @Override
  public User saveUser(User user) {
    return this.userRepository.save(user);
  }
}
