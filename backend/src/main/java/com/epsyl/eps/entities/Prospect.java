package com.epsyl.eps.entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.epsyl.eps.serialization.ObjectIdDeserrializer;
import com.epsyl.eps.serialization.ObjectIdSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserrializer.class)
  public ObjectId _id;

  public String firstName;

  public String lastName;

  public String trigramme;

  public String email;

  public String phone;

  public String profil;

  public Date contactDate;

  public Date meetingDate;

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserrializer.class)
  public ObjectId bum; // ObjectId

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserrializer.class)
  public ObjectId rh; // ObjectId

  public String source;

  public String pretentionSalarial;

  public String niveauEtude;

  public Boolean frenchNationality;
  public String nationality;

  public String experience;

  public String englishLevel;

  public String avis;

  public String disponibilite;

  public String[] mobiliteGeo;

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserrializer.class)
  public ObjectId cv; // ObjectId

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserrializer.class)
  public ObjectId grille; // ObjectId

  public Integer pr;

  public Boolean prValidated;

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserrializer.class)
  public ObjectId dc; // ObjectId

  @JsonProperty
  public PushQualif[] pushQualif;

  @CreatedDate
  public LocalDateTime createdDate;
}
