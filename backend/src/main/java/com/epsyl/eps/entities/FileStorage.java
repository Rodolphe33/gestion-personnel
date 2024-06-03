package com.epsyl.eps.entities;

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
@Document(collection = "fileStorages")
public class FileStorage {
  @Id
  public String _id;
  public String filename;
  public String contentType;
  public byte[] data;

}
