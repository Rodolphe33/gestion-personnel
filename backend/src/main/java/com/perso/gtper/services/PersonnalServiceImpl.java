package com.perso.gtper.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perso.gtper.emailings.MailBumAdded;
import com.perso.gtper.emailings.MailGrilleAdded;
import com.perso.gtper.entities.Personnal;
import com.perso.gtper.exception.ResourceNotFoundException;
import com.perso.gtper.repositories.PersonnalRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonnalServiceImpl implements PersonnalService {

  @Autowired
  private PersonnalRepository personnalRepository;

  @Autowired
  private MailBumAdded mailBumAdded;

  @Autowired
  private MailGrilleAdded mailGrilleAdded;

  // All personnals
  public List<Personnal> allPersonnals() {
    return personnalRepository.findAll();
  }

  // One personnal
  public Optional<Personnal> getPersonnalByID(ObjectId _id) {
    return personnalRepository.findById(_id);
  }

  // Create personnal
  public Personnal createPersonnal(Personnal personnal) {
    return personnalRepository.save(personnal);
  }

  // Update personnal
  public Personnal updatePersonnal(ObjectId id, Personnal updatedPersonnal) {
    Personnal existingPersonnal = personnalRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Personnal not found"));

    updatedPersonnal.set_id(existingPersonnal.get_id());

    Personnal savedPersonnal = personnalRepository.save(updatedPersonnal);

    if (savedPersonnal.getGrille() != null) {
      try {
          mailGrilleAdded.notifyGrilleAdded(savedPersonnal);
      } catch (Exception e) {
          System.err.println("Erreur lors de l'envoi du mail Grille : " + e.getMessage());
          throw new RuntimeException("Erreur lors de l'envoi du mail Grille : " + e);
      }
    }
  
    if (savedPersonnal.getBum() != null) {
      try {
          mailBumAdded.notifyBumAdded(savedPersonnal);
      } catch (Exception e) {
          System.err.println("Erreur lors de l'envoi du mail Bum : " + e.getMessage());
          // Log exception without throwing to continue with the update
        }
    }
  
    return savedPersonnal;
  }

  public Optional<Personnal> deletePersonnalById(ObjectId _id) {
    return personnalRepository.findById(_id).flatMap(personnal -> {
      personnalRepository.deleteById(personnal.get_id());
      return Optional.of(personnal);
    });
  }

  public boolean trigramExist(String trigramme) {
    return personnalRepository.existsByTrigramme(trigramme);
  }
}
