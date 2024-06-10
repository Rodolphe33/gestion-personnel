package com.epsyl.eps.entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.epsyl.eps.configuration_global.ObjectIdDeserrializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
  @JsonDeserialize(using = ObjectIdDeserrializer.class)
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

  public String bum; // ObjectId

  public String rh; // ObjectId

  public String source;

  public Double pretentionSalariale;

  public String niveauEtude;

  public String disponibilite;

  public String mobiliteGeo;

  public Boolean goNogo;

  public String cv; // ObjectId

  public String grille; // ObjectId

  public Integer pr;

  public String dc; // ObjectId

  @JsonProperty
  public PushQualif pushQualif;

  @CreatedDate
  public LocalDateTime createdDate;
}
