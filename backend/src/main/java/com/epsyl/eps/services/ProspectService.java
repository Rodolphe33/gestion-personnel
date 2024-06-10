package com.epsyl.eps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.epsyl.eps.entities.Prospect;
import com.epsyl.eps.repositories.ProspectRepository;

@Service
public class ProspectService {

  @Autowired
  private ProspectRepository prospectRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public Prospect getProspectWithUserDetail(String prospectId) {
    Aggregation aggregation = Aggregation.newAggregation(
      Aggregation.match(Criteria.where("_id").is(prospectId)),
      Aggregation.lookup("users", "bum", "_id", "bumDetails"),
      Aggregation.lookup("users", "rh", "_id", "rhDetails")
    );
    AggregationResults<Prospect> results = mongoTemplate.aggregate(aggregation, "prospects", Prospect.class);
    return results.getUniqueMappedResult();
  }

  // All prospects
  public List<Prospect> allProspects() {
    return prospectRepository.findAll();
  }

  // One prospect
  public Optional<Prospect> getProspectByID(String _id) {
    return prospectRepository.findById(_id);
  }

  public Prospect save(Prospect prospect) {
    return prospectRepository.save(prospect);
  }

  public Prospect updateProspect(String id, Prospect updatedProspect) {
    // Récupérer le prospect existant depuis la base de données
    Prospect existingProspect = prospectRepository.findById(id).orElseThrow(() -> new RuntimeException("Prospect not found"));

    existingProspect.setFirstName(updatedProspect.getFirstName());
    existingProspect.setLastName(updatedProspect.getLastName());
    existingProspect.setTrigramme(updatedProspect.getTrigramme());
    existingProspect.setEmail(updatedProspect.getEmail());
    existingProspect.setPhone(updatedProspect.getPhone());
    existingProspect.setProfil(updatedProspect.getProfil());
    existingProspect.setDateContact(updatedProspect.getDateContact());
    existingProspect.setDateEntretien(updatedProspect.getDateEntretien());
    existingProspect.setStatutProspect(updatedProspect.getStatutProspect());
    existingProspect.setBum(updatedProspect.getBum());
    existingProspect.setRh(updatedProspect.getRh());
    existingProspect.setSource(updatedProspect.getSource());
    existingProspect.setPretentionSalariale(updatedProspect.getPretentionSalariale());
    existingProspect.setNiveauEtude(updatedProspect.getNiveauEtude());
    existingProspect.setDisponibilite(updatedProspect.getDisponibilite());
    existingProspect.setMobiliteGeo(updatedProspect.getMobiliteGeo());
    existingProspect.setGoNogo(updatedProspect.getGoNogo());
    existingProspect.setCv(updatedProspect.getCv());
    existingProspect.setGrille(updatedProspect.getGrille());
    existingProspect.setPr(updatedProspect.getPr());
    existingProspect.setDc(updatedProspect.getDc());
    existingProspect.setPushQualif(updatedProspect.getPushQualif());
    existingProspect.setCreatedDate(updatedProspect.getCreatedDate());

    return existingProspect;
  }

  public Optional<Prospect> deleteProspectById(String _id) {
    return prospectRepository.findById(_id).flatMap(prospect -> {
      prospectRepository.deleteById(prospect.get_id());
      return Optional.of(prospect);
    });
  }

  public boolean trigramExist(String trigramme) {
    return prospectRepository.existsByTrigramme(trigramme);
  }
}
