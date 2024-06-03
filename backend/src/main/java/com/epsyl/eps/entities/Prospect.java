package com.epsyl.eps.entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "prospects")
public class Prospect {

  @Id
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

  public double pretentionSalariale;

  public String niveauEtude;

  public String disponibilite;

  public String mobiliteGeo;

  public String cv;

  public String grille;

  public byte pr;

  public String dc;

  @JsonProperty
  public PushQualif pushQualif;

  @CreatedDate
  public LocalDateTime createdDate;
}
