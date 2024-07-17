package com.perso.gtper.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.perso.gtper.entities.Personnal;

@Repository
public interface PersonnalRepository extends MongoRepository<Personnal, ObjectId> {

  /*
   * Vérifier si le trigramme existe
   */
  boolean existsByTrigramme(String trigramme);
}
