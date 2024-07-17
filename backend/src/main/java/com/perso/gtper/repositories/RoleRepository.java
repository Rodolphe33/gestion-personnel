package com.perso.gtper.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.perso.gtper.entities.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(String name);
}
