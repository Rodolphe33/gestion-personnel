package com.epsyl.eps.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.epsyl.eps.entities.User;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  User findBy_id(String _id);
}