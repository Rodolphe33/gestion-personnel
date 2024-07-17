package com.perso.gtper.serialization;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.perso.gtper.entities.FileStorage;

public class FileStorageDeserializer extends JsonDeserializer<FileStorage> {
  @Override
  public FileStorage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
    ObjectMapper mapper = (ObjectMapper) p.getCodec();
    ObjectNode node = mapper.readTree(p);

    FileStorage fileStorage = new FileStorage();
    fileStorage.set_id(new ObjectId(node.get("_id").asText()));
    fileStorage.setFilename(node.get("filename").asText());
    fileStorage.setContentType(node.get("contentType").asText());
    fileStorage.setData(node.get("data").binaryValue());

    return fileStorage;
  }
}