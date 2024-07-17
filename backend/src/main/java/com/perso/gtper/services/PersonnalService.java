package com.perso.gtper.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.perso.gtper.entities.Personnal;

public interface PersonnalService {
  List<Personnal> allPersonnals();
  Optional<Personnal> getPersonnalByID(ObjectId _id);
  Personnal createPersonnal(Personnal personnal);
  Personnal updatePersonnal(ObjectId id, Personnal updatedPersonnal);
  Optional<Personnal> deletePersonnalById(ObjectId _id);
  boolean trigramExist(String trigramme);
}
