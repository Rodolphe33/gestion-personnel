package com.perso.gtper.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.perso.gtper.entities.User;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  User findBy_id(ObjectId _id);
}