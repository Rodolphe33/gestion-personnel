package com.perso.gtper.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.perso.gtper.entities.ArchivePersonnal;
import com.perso.gtper.entities.FileStorage;
import com.perso.gtper.serialization.FileStorageDeserializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArchivePersonnalDTO {

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

  public ArchivePersonnalDTO(ArchivePersonnal personnal, FileStorage cv) {
    this._id = personnal.get_id().toString();
    this.firstName = personnal.getFirstName();
    this.lastName = personnal.getLastName();
    this.trigramme = personnal.getTrigramme();
    this.email = personnal.getEmail();
    this.phone = personnal.getPhone();
    this.profil = personnal.getProfil();
    this.niveauEtude = personnal.getNiveauEtude();
    this.avis = personnal.getAvis();
    this.cv = cv;
    this.createdDate = personnal.getCreatedDate();
  }
}
