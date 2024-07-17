package com.perso.gtper.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.perso.gtper.entities.ArchivePersonnal;

@Repository
public interface ArchivePersonnalRepository extends MongoRepository<ArchivePersonnal, String> {

}
