package com.epsyl.eps.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.epsyl.eps.entities.ArchiveProspect;

@Repository
public interface ArchiveProspectRepository extends MongoRepository<ArchiveProspect, String> {

}
