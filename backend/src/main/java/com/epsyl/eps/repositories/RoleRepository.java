package com.epsyl.eps.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epsyl.eps.entities.RoleEntity;
import com.epsyl.eps.enums.ERole;


public interface RoleRepository extends MongoRepository<RoleEntity, String> {
  Optional<RoleEntity> findByName(ERole name);
}
