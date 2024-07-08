package com.epsyl.eps.services;

import java.util.Optional;

import com.epsyl.eps.entities.ArchiveProspect;

public interface ArchiveProspectService {
  Iterable<ArchiveProspect> listAll();
	Optional<ArchiveProspect> getArchiveProspectByID(String id);
	ArchiveProspect save(ArchiveProspect user);
  Optional<ArchiveProspect> deleteArchive(String _id);
}
