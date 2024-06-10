package com.epsyl.eps.services;

import java.util.List;
import java.util.Optional;

import com.epsyl.eps.entities.Role;
import com.epsyl.eps.entities.User;

public interface UserService {
	Role addNewRole(Role role);

	void addRoleToUser(User user, List<Role> roles);

	// CRUD operations
	Iterable<User> listAll();
	Optional<User> getUserByID(String id);
	User saveUser(User user);
  Optional<User> deleteUser(String _id);
}