package com.epsyl.eps.dtos;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.epsyl.eps.entities.PushQualif;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProspectDTO {
  public String _id;
  public String firstName;
  public String lastName;
  public String trigramme;
  public String email;
  public String phone;
  public String profil;
  public Date dateContact;
  public Date dateEntretien;
  public String statutProspect;
  public String bum;
  public String rh;
  public String source;
  public Double pretentionSalariale;
  public String niveauEtude;
  public String disponibilite;
  public String mobiliteGeo;
  public String cv;
  public String grille;
  public Integer pr;
  public String dc;
  public PushQualif pushQualif;

  @CreatedDate
  public LocalDateTime createdDate;
}
