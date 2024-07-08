package com.epsyl.eps.dtos;

import java.time.LocalDateTime;
import java.util.Date;

import com.epsyl.eps.entities.FileStorage;
import com.epsyl.eps.entities.Prospect;
import com.epsyl.eps.entities.PushQualif;
import com.epsyl.eps.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProspectDTO {

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

  public ProspectDTO(Prospect prospect, User bum, User rh, FileStorage cv, FileStorage grille, FileStorage dc) {
    this._id = prospect.get_id().toString();
    this.firstName = prospect.getFirstName();
    this.lastName = prospect.getLastName();
    this.trigramme = prospect.getTrigramme();
    this.email = prospect.getEmail();
    this.phone = prospect.getPhone();
    this.profil = prospect.getProfil();
    this.contactDate = prospect.getContactDate();
    this.meetingDate = prospect.getMeetingDate();
    this.bum = bum;
    this.rh = rh;
    this.source = prospect.getSource();
    this.pretentionSalarial = prospect.getPretentionSalarial();
    this.niveauEtude = prospect.getNiveauEtude();
    this.frenchNationality = prospect.getFrenchNationality();
    this.nationality = prospect.getNationality();
    this.experience = prospect.getExperience();
    this.englishLevel = prospect.getEnglishLevel();
    this.avis = prospect.getAvis();
    this.disponibilite = prospect.getDisponibilite();
    this.mobiliteGeo = prospect.getMobiliteGeo();
    this.disponibilite = prospect.getDisponibilite();
    this.mobiliteGeo = prospect.getMobiliteGeo();
    this.cv = cv;
    this.grille = grille;
    this.pr = prospect.getPr();
    this.prValidated = prospect.getPrValidated();
    this.dc = dc;
    this.pushQualif = prospect.getPushQualif();
    this.createdDate = prospect.getCreatedDate();
  }
}