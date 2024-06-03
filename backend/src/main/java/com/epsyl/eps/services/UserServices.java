package com.epsyl.eps.services;

import java.util.List;

import com.epsyl.eps.entities.Role;
import com.epsyl.eps.entities.User;

public interface UserServices {
	Role addNewRole(Role role);

	void addRoleToUser(User user, List<Role> roles);
}