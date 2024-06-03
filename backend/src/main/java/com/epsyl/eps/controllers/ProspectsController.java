package com.epsyl.eps.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.epsyl.eps.entities.Prospect;
import com.epsyl.eps.services.ProspectServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/prospect")
public class ProspectsController {

  @Autowired
  private ProspectServices prospectServices;

  // GET /api/v1/prospect/all
  @GetMapping("/all")
  public List<Prospect> getAllProspects() {
    return prospectServices.allProspects();
  }

  // GET /api/v1/prospect/id
  @RequestMapping("/{id}")
  public ResponseEntity<Prospect> getProspectById(@PathVariable String _id) {
    Optional<Prospect> prospect = prospectServices.getProspectByID(_id);
    if (prospect.isPresent()) {
      return ResponseEntity.ok(prospect.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // GET /api/v1/prospect/trigram
  @GetMapping("/trigram")
  public boolean checkTrigram(@RequestParam String trigramme) {
    return prospectServices.trigramExist(trigramme);
  }

  // POST /api/v1/prospect/save
  @PostMapping("/save")
  public Prospect saveProspect(@RequestBody Prospect prospect) {
    return prospectServices.saveOrUpdateProspect(prospect);
  }

  // PUT /api/v1/prospect/edit/id
  @PutMapping("/edit/{id}")
  public ResponseEntity<Prospect> updateProspect(@PathVariable String _id, @RequestBody Prospect prospectDetails) {
    Optional<Prospect> prospectOptional = prospectServices.getProspectByID(_id);
    if (prospectOptional.isPresent()) {
      Prospect prospect = prospectOptional.get();
      // Update fields here
      prospect.setFirstName(prospectDetails.getFirstName());
      prospect.setLastName(prospectDetails.getLastName());
      prospect.setTrigramme(prospectDetails.getTrigramme());
      prospect.setEmail(prospectDetails.getEmail());
      prospect.setPhone(prospectDetails.getPhone());
      prospect.setProfil(prospectDetails.getProfil());
      prospect.setDateContact(prospectDetails.getDateContact());
      prospect.setDateEntretien(prospectDetails.getDateEntretien());
      prospect.setStatutProspect(prospectDetails.getStatutProspect());
      prospect.setBum(prospectDetails.getBum());
      prospect.setRh(prospectDetails.getRh());
      prospect.setSource(prospectDetails.getSource());
      prospect.setPretentionSalariale(prospectDetails.getPretentionSalariale());
      prospect.setNiveauEtude(prospectDetails.getNiveauEtude());
      prospect.setDisponibilite(prospectDetails.getDisponibilite());
      prospect.setMobiliteGeo(prospectDetails.getMobiliteGeo());
      prospect.setCv(prospectDetails.getCv());
      prospect.setGrille(prospectDetails.getGrille());
      prospect.setPr(prospectDetails.getPr());
      prospect.setDc(prospectDetails.getDc());
      prospect.setPushQualif(prospectDetails.getPushQualif());
      // Save updated prospect
      Prospect updatedProspect = prospectServices.saveOrUpdateProspect(prospect);
      return ResponseEntity.ok(updatedProspect);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // DELETE /api/v1/prospect/delete/id
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteProspect(@PathVariable String _id) {
    prospectServices.deleteProspectById(_id);
    return ResponseEntity.noContent().build();
  }

}
