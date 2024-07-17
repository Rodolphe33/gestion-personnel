package com.perso.gtper.services;

import java.util.Optional;

import com.perso.gtper.entities.ArchivePersonnal;

public interface ArchivePersonnalService {
  Iterable<ArchivePersonnal> listAll();
	Optional<ArchivePersonnal> getArchivePersonnalByID(String id);
	ArchivePersonnal save(ArchivePersonnal user);
  Optional<ArchivePersonnal> deleteArchive(String _id);
}
