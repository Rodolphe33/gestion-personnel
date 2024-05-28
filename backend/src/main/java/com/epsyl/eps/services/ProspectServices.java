package com.epsyl.eps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsyl.eps.entities.ProspectEntity;
import com.epsyl.eps.repositories.ProspectRepository;

@Service
public class ProspectServices {

  @Autowired
  private ProspectRepository prospectRepository;

  public List<ProspectEntity> allProspects() {
    return prospectRepository.findAll();
  }

  public Optional<ProspectEntity> getProspectByID(String _id) {
    return prospectRepository.findById(_id);
  }

  public ProspectEntity saveOrUpdateProspect(ProspectEntity prospect) {
    return prospectRepository.save(prospect);
  }

  public Optional<ProspectEntity> deleteProspectById(String _id) {
    return prospectRepository.findById(_id).flatMap(prospect -> {
      prospectRepository.deleteById(prospect.get_id());
      return Optional.of(prospect);
    });
  }

  public boolean trigramExist(String trigramme) {
    return prospectRepository.existsByTrigramme(trigramme);
  }
}
