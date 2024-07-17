package com.perso.gtper.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "file_storages")
public class FileStorage {
  @Id
  public ObjectId _id;
  public String filename;
  public String contentType;
  public byte[] data;
}