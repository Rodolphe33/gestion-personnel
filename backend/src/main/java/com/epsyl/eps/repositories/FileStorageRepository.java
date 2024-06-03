package com.epsyl.eps.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.epsyl.eps.entities.FileStorage;

@Repository
public interface FileStorageRepository extends MongoRepository<FileStorage, String> {

}
