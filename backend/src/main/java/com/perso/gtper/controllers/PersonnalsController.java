package com.perso.gtper.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perso.gtper.dtos.PersonnalDTO;
import com.perso.gtper.entities.FileStorage;
import com.perso.gtper.entities.Personnal;
import com.perso.gtper.entities.User;
import com.perso.gtper.repositories.FileStorageRepository;
import com.perso.gtper.repositories.UserRepository;
import com.perso.gtper.services.PersonnalService;

@RestController
@RequestMapping("/api/v1/personnal")
public class PersonnalsController {

  @Autowired
  private PersonnalService personnalService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FileStorageRepository fileStorageRepository;

  // GET /api/v1/personnal/all
  @GetMapping("/all")
  public List<PersonnalDTO> getAllPersonnals() {
    List<Personnal> personnals = personnalService.allPersonnals();
    List<PersonnalDTO> personnalDTOs = new ArrayList<>();
    for (Personnal personnal : personnals) {
      User bum = null;
      User rh = null;
      FileStorage cv = null;
      FileStorage grille = null;
      FileStorage dc = null;

      if (personnal.bum != null) {
        bum = userRepository.findBy_id(personnal.bum);
      }

      if (personnal.rh != null) {
        rh = userRepository.findById(personnal.rh).orElse(null);
      }

      if (personnal.cv != null) {
        cv = fileStorageRepository.findById(personnal.cv).orElse(null);
      }

      if (personnal.grille != null) {
        grille = fileStorageRepository.findById(personnal.grille).orElse(null);
      }

      if (personnal.dc != null) {
        dc = fileStorageRepository.findById(personnal.dc).orElse(null);
      }

      PersonnalDTO personnalDTO = new PersonnalDTO(personnal,bum, rh, cv, grille, dc);
      personnalDTOs.add(personnalDTO);
    }
    return personnalDTOs;
  }

  // GET /api/v1/personnal/id
  @RequestMapping("/{id}")
  public ResponseEntity<Personnal> getPersonnalById(@PathVariable ObjectId _id) {
    Optional<Personnal> personnal = personnalService.getPersonnalByID(_id);
    if (personnal.isPresent()) {
      return ResponseEntity.ok(personnal.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // GET /api/v1/personnal/trigram
  @GetMapping("/trigram")
  public boolean checkTrigram(@RequestParam String trigramme) {
    return personnalService.trigramExist(trigramme);
  }

  // POST /api/v1/personnal/save
  @PostMapping("/save")
  public Personnal savePersonnal(@RequestBody Personnal personnal) {
    return personnalService.createPersonnal(personnal);
  }

  // PUT /api/v1/personnal/id
  @PutMapping("/{id}")
  public ResponseEntity<Personnal> updatePersonnal(@PathVariable ObjectId id, @RequestBody Personnal personnal) {
    Personnal updatedPersonnal = personnalService.updatePersonnal(id, personnal);
    return ResponseEntity.ok(updatedPersonnal);
  }

  // DELETE /api/v1/personnal/delete/id
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deletePersonnal(@PathVariable ObjectId id) {
    personnalService.deletePersonnalById(id);
    return ResponseEntity.noContent().build();
  }
}
