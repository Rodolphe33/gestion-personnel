package com.perso.gtper.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.perso.gtper.entities.FileStorage;

@Repository
public interface FileStorageRepository extends MongoRepository<FileStorage, ObjectId> { }
