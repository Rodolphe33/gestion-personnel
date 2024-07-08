package com.epsyl.eps.entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.epsyl.eps.serialization.ObjectIdDeserrializer;
import com.epsyl.eps.serialization.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "archive_prospect")
public class ArchiveProspect {

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserrializer.class)
  public ObjectId _id;

  public String firstName;

  public String lastName;

  public String trigramme;

  public String email;

  public String phone;

  public String profil;

  public String niveauEtude;

  public String avis;

  public String cv; // ObjectId

  @CreatedDate
  public LocalDateTime createdDate;
}
