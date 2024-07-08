package com.epsyl.eps.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epsyl.eps.emailings.MailBumAdded;
import com.epsyl.eps.emailings.MailGrilleAdded;
import com.epsyl.eps.entities.Prospect;
import com.epsyl.eps.exception.ResourceNotFoundException;
import com.epsyl.eps.repositories.ProspectRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProspectServiceImpl implements ProspectService {

  @Autowired
  private ProspectRepository prospectRepository;

  @Autowired
  private MailBumAdded mailBumAdded;

  @Autowired
  private MailGrilleAdded mailGrilleAdded;

  // All prospects
  public List<Prospect> allProspects() {
    return prospectRepository.findAll();
  }

  // One prospect
  public Optional<Prospect> getProspectByID(ObjectId _id) {
    return prospectRepository.findById(_id);
  }

  // Create prospect
  public Prospect createProspect(Prospect prospect) {
    return prospectRepository.save(prospect);
  }

  // Update prospect
  public Prospect updateProspect(ObjectId id, Prospect updatedProspect) {
    Prospect existingProspect = prospectRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Prospect not found"));

    updatedProspect.set_id(existingProspect.get_id());

    Prospect savedProspect = prospectRepository.save(updatedProspect);

    if (savedProspect.getGrille() != null) {
      try {
          mailGrilleAdded.notifyGrilleAdded(savedProspect);
      } catch (Exception e) {
          System.err.println("Erreur lors de l'envoi du mail Grille : " + e.getMessage());
          throw new RuntimeException("Erreur lors de l'envoi du mail Grille : " + e);
      }
    }
  
    if (savedProspect.getBum() != null) {
      try {
          mailBumAdded.notifyBumAdded(savedProspect);
      } catch (Exception e) {
          System.err.println("Erreur lors de l'envoi du mail Bum : " + e.getMessage());
          // Log exception without throwing to continue with the update
        }
    }
  
    return savedProspect;
  }

  public Optional<Prospect> deleteProspectById(ObjectId _id) {
    return prospectRepository.findById(_id).flatMap(prospect -> {
      prospectRepository.deleteById(prospect.get_id());
      return Optional.of(prospect);
    });
  }

  public boolean trigramExist(String trigramme) {
    return prospectRepository.existsByTrigramme(trigramme);
  }
}
