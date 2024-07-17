package com.perso.gtper.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perso.gtper.entities.Role;
import com.perso.gtper.entities.User;
import com.perso.gtper.repositories.RoleRepository;
import com.perso.gtper.repositories.UserRepository;

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
    User userFromDB = userRepository.findByEmail(user.getEmail()).orElse(null);

    if(userFromDB != null) {
      roles.forEach(role -> {
        Role roleFromDB = roleRepository.findByName(role.getName().toString()).orElse(null);

        if(roleFromDB != null && !userFromDB.getRoles().contains(roleFromDB)) {
          userFromDB.getRoles().add(roleFromDB);
        }
      });
      userRepository.save(userFromDB);
    }
  }

  public Optional<User> getUserByID(ObjectId userId) {
    return this.userRepository.findById(userId);
  }

  public Iterable<User> listAll() {
    return this.userRepository.findAll();
  }

  public Optional<User> deleteUser(ObjectId _id) {
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
