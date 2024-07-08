package com.epsyl.eps.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.epsyl.eps.dtos.ProspectDTO;
import com.epsyl.eps.entities.FileStorage;
import com.epsyl.eps.entities.Prospect;
import com.epsyl.eps.entities.User;
import com.epsyl.eps.repositories.FileStorageRepository;
import com.epsyl.eps.repositories.UserRepository;
import com.epsyl.eps.services.ProspectService;

@RestController
@RequestMapping("/api/v1/prospect")
public class ProspectsController {

  @Autowired
  private ProspectService prospectService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FileStorageRepository fileStorageRepository;

  // GET /api/v1/prospect/all
  @GetMapping("/all")
  public List<ProspectDTO> getAllProspects() {
    List<Prospect> prospects = prospectService.allProspects();
    List<ProspectDTO> prospectDTOs = new ArrayList<>();
    for (Prospect prospect : prospects) {
      User bum = null;
      User rh = null;
      FileStorage cv = null;
      FileStorage grille = null;
      FileStorage dc = null;

      if (prospect.bum != null) {
        bum = userRepository.findBy_id(prospect.bum);
      }

      if (prospect.rh != null) {
        rh = userRepository.findById(prospect.rh).orElse(null);
      }

      if (prospect.cv != null) {
        cv = fileStorageRepository.findById(prospect.cv).orElse(null);
      }

      if (prospect.grille != null) {
        grille = fileStorageRepository.findById(prospect.grille).orElse(null);
      }

      if (prospect.dc != null) {
        dc = fileStorageRepository.findById(prospect.dc).orElse(null);
      }

      ProspectDTO prospectDTO = new ProspectDTO(prospect,bum, rh, cv, grille, dc);
      prospectDTOs.add(prospectDTO);
    }
    return prospectDTOs;
  }

  // GET /api/v1/prospect/id
  @RequestMapping("/{id}")
  public ResponseEntity<Prospect> getProspectById(@PathVariable ObjectId _id) {
    Optional<Prospect> prospect = prospectService.getProspectByID(_id);
    if (prospect.isPresent()) {
      return ResponseEntity.ok(prospect.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // GET /api/v1/prospect/trigram
  @GetMapping("/trigram")
  public boolean checkTrigram(@RequestParam String trigramme) {
    return prospectService.trigramExist(trigramme);
  }

  // POST /api/v1/prospect/save
  @PostMapping("/save")
  public Prospect saveProspect(@RequestBody Prospect prospect) {
    return prospectService.createProspect(prospect);
  }

  // PUT /api/v1/prospect/id
  @PutMapping("/{id}")
  public ResponseEntity<Prospect> updateProspect(@PathVariable ObjectId id, @RequestBody Prospect prospect) {
    Prospect updatedProspect = prospectService.updateProspect(id, prospect);
    return ResponseEntity.ok(updatedProspect);
  }

  // DELETE /api/v1/prospect/delete/id
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteProspect(@PathVariable ObjectId id) {
    prospectService.deleteProspectById(id);
    return ResponseEntity.noContent().build();
  }
}
