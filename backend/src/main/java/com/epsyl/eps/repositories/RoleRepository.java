package com.epsyl.eps.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epsyl.eps.entities.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(String name);
}
