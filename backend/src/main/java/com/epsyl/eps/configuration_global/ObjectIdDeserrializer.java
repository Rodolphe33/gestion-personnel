package com.epsyl.eps.configuration_global;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ObjectIdDeserrializer extends JsonDeserializer<ObjectId> {

  @Override
  public ObjectId deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
    return new ObjectId(jsonParser.getText());
  }
}