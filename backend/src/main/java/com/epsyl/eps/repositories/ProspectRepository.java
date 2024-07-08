package com.epsyl.eps.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.epsyl.eps.entities.Prospect;

@Repository
public interface ProspectRepository extends MongoRepository<Prospect, ObjectId> {

  /*
   * Vérifier si le trigramme existe
   */
  boolean existsByTrigramme(String trigramme);
}
