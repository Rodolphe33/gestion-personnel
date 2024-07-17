package com.perso.gtper.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.gtper.entities.ArchivePersonnal;
import com.perso.gtper.repositories.ArchivePersonnalRepository;

@Service
public class ArchivePersonnalServiceImpl implements ArchivePersonnalService {

  @Autowired
  private ArchivePersonnalRepository archivePersonnalRepository;

  @Override
  public Iterable<ArchivePersonnal> listAll() {
    return this.archivePersonnalRepository.findAll();
  }

  @Override

  public Optional<ArchivePersonnal> getArchivePersonnalByID(String id) {
    return this.archivePersonnalRepository.findById(id);
  }

  public ArchivePersonnal save(ArchivePersonnal personnal) {
    return this.archivePersonnalRepository.save(personnal);
  }

  public Optional<ArchivePersonnal> deleteArchive(String _id) {
    return this.archivePersonnalRepository.findById(_id).flatMap(personnal -> {
      archivePersonnalRepository.deleteById(personnal.get_id().toString());
      return Optional.of(personnal);
    });
  }

}
