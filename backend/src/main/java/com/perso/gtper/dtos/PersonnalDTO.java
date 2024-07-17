package com.perso.gtper.dtos;

import java.time.LocalDateTime;
import java.util.Date;

import com.perso.gtper.entities.FileStorage;
import com.perso.gtper.entities.Personnal;
import com.perso.gtper.entities.PushQualif;
import com.perso.gtper.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonnalDTO {

  private String _id;
  private String firstName;
  private String lastName;
  private String trigramme;
  private String email;
  private String phone;
  private String profil;
  private Date contactDate;
  private Date meetingDate;
  private User bum;
  private User rh;
  private String source;
  private String pretentionSalarial;
  private String niveauEtude;
  private Boolean frenchNationality;
  private String nationality;
  private String experience;
  private String englishLevel;
  private String avis;
  private String disponibilite;
  private String[] mobiliteGeo;
  private FileStorage cv;
  private FileStorage grille;
  private Integer pr;
  private Boolean prValidated;
  private FileStorage dc;
  private PushQualif[] pushQualif;
  private LocalDateTime createdDate;

  public PersonnalDTO(Personnal personnal, User bum, User rh, FileStorage cv, FileStorage grille, FileStorage dc) {
    this._id = personnal.get_id().toString();
    this.firstName = personnal.getFirstName();
    this.lastName = personnal.getLastName();
    this.trigramme = personnal.getTrigramme();
    this.email = personnal.getEmail();
    this.phone = personnal.getPhone();
    this.profil = personnal.getProfil();
    this.contactDate = personnal.getContactDate();
    this.meetingDate = personnal.getMeetingDate();
    this.bum = bum;
    this.rh = rh;
    this.source = personnal.getSource();
    this.pretentionSalarial = personnal.getPretentionSalarial();
    this.niveauEtude = personnal.getNiveauEtude();
    this.frenchNationality = personnal.getFrenchNationality();
    this.nationality = personnal.getNationality();
    this.experience = personnal.getExperience();
    this.englishLevel = personnal.getEnglishLevel();
    this.avis = personnal.getAvis();
    this.disponibilite = personnal.getDisponibilite();
    this.mobiliteGeo = personnal.getMobiliteGeo();
    this.disponibilite = personnal.getDisponibilite();
    this.mobiliteGeo = personnal.getMobiliteGeo();
    this.cv = cv;
    this.grille = grille;
    this.pr = personnal.getPr();
    this.prValidated = personnal.getPrValidated();
    this.dc = dc;
    this.pushQualif = personnal.getPushQualif();
    this.createdDate = personnal.getCreatedDate();
  }
}