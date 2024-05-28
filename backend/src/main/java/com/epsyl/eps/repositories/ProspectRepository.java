package com.epsyl.eps.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.epsyl.eps.entities.ProspectEntity;

@Repository
public interface ProspectRepository extends MongoRepository<ProspectEntity, String> {
  /*
   * Vérifier si le trigramme existe
   */
  boolean existsByTrigramme(String trigramme);
}
