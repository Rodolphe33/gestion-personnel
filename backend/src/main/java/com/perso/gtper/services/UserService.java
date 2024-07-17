package com.perso.gtper.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.perso.gtper.entities.Role;
import com.perso.gtper.entities.User;

public interface UserService {
	Role addNewRole(Role role);

	void addRoleToUser(User user, List<Role> roles);

	// CRUD operations
	Iterable<User> listAll();
	Optional<User> getUserByID(ObjectId id);
	User saveUser(User user);
  Optional<User> deleteUser(ObjectId _id);
}