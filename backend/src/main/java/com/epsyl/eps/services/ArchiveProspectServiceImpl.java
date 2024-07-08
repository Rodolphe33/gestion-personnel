package com.epsyl.eps.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsyl.eps.entities.ArchiveProspect;
import com.epsyl.eps.repositories.ArchiveProspectRepository;

@Service
public class ArchiveProspectServiceImpl implements ArchiveProspectService {

  @Autowired
  private ArchiveProspectRepository archiveProspectRepository;

  @Override
  public Iterable<ArchiveProspect> listAll() {
    return this.archiveProspectRepository.findAll();
  }

  @Override

  public Optional<ArchiveProspect> getArchiveProspectByID(String id) {
    return this.archiveProspectRepository.findById(id);
  }

  public ArchiveProspect save(ArchiveProspect prospect) {
    return this.archiveProspectRepository.save(prospect);
  }

  public Optional<ArchiveProspect> deleteArchive(String _id) {
    return this.archiveProspectRepository.findById(_id).flatMap(prospect -> {
      archiveProspectRepository.deleteById(prospect.get_id().toString());
      return Optional.of(prospect);
    });
  }

}
