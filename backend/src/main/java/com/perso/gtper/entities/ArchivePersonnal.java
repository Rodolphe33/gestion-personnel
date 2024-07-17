package com.perso.gtper.entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.perso.gtper.serialization.ObjectIdDeserrializer;
import com.perso.gtper.serialization.ObjectIdSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "archive_personnal")
public class ArchivePersonnal {

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
