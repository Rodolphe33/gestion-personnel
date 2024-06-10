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
  private Date dateContact;
  private Date dateEntretien;
  private String statutProspect;
  private User bum;
  private User rh;
  private String source;
  private Double pretentionSalariale;
  private String niveauEtude;
  private String disponibilite;
  private String mobiliteGeo;
  private Boolean goNogo;
  private FileStorage cv;
  private FileStorage grille;
  private Integer pr;
  private FileStorage dc;
  private PushQualif pushQualif;
  private LocalDateTime createdDate;

  public ProspectDTO(Prospect prospect, User bum, User rh, FileStorage cv, FileStorage grille, FileStorage dc) {
    this._id = prospect.get_id();
    this.firstName = prospect.getFirstName();
    this.lastName = prospect.getLastName();
    this.trigramme = prospect.getTrigramme();
    this.email = prospect.getEmail();
    this.phone = prospect.getPhone();
    this.profil = prospect.getProfil();
    this.dateContact = prospect.getDateContact();
    this.dateEntretien = prospect.getDateEntretien();
    this.statutProspect = prospect.getStatutProspect();
    this.bum = bum;
    this.rh = rh;
    this.source = prospect.getSource();
    this.pretentionSalariale = prospect.getPretentionSalariale();
    this.niveauEtude = prospect.getNiveauEtude();
    this.disponibilite = prospect.getDisponibilite();
    this.mobiliteGeo = prospect.getMobiliteGeo();
    this.goNogo = prospect.getGoNogo();
    this.cv = cv;
    this.grille = grille;
    this.pr = prospect.getPr();
    this.dc = dc;
    this.pushQualif = prospect.getPushQualif();
    this.createdDate = prospect.getCreatedDate();
  }
}
