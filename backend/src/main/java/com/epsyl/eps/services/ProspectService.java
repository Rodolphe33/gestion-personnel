package com.epsyl.eps.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.epsyl.eps.entities.Prospect;

public interface ProspectService {
  List<Prospect> allProspects();
  Optional<Prospect> getProspectByID(ObjectId _id);
  Prospect createProspect(Prospect prospect);
  Prospect updateProspect(ObjectId id, Prospect updatedProspect);
  Optional<Prospect> deleteProspectById(ObjectId _id);
  boolean trigramExist(String trigramme);
}
