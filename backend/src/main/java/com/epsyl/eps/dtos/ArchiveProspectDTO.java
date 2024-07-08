package com.epsyl.eps.dtos;

import java.time.LocalDateTime;

import com.epsyl.eps.entities.ArchiveProspect;
import com.epsyl.eps.entities.FileStorage;
import com.epsyl.eps.serialization.FileStorageDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArchiveProspectDTO {

  private String _id;
  private String firstName;
  private String lastName;
  private String trigramme;
  private String email;
  private String phone;
  private String profil;
  private String niveauEtude;
  private String avis;

  @JsonDeserialize(using = FileStorageDeserializer.class)
  private FileStorage cv; // ObjectId

  private LocalDateTime createdDate;

  public ArchiveProspectDTO(ArchiveProspect prospect, FileStorage cv) {
    this._id = prospect.get_id().toString();
    this.firstName = prospect.getFirstName();
    this.lastName = prospect.getLastName();
    this.trigramme = prospect.getTrigramme();
    this.email = prospect.getEmail();
    this.phone = prospect.getPhone();
    this.profil = prospect.getProfil();
    this.niveauEtude = prospect.getNiveauEtude();
    this.avis = prospect.getAvis();
    this.cv = cv;
    this.createdDate = prospect.getCreatedDate();
  }
}
