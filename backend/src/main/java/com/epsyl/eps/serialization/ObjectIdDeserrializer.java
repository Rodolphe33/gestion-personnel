package com.epsyl.eps.serialization;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ObjectIdDeserrializer extends StdDeserializer<ObjectId> {

  public ObjectIdDeserrializer() {
    super(ObjectId.class);
  }

  @Override
  public ObjectId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    try {
      String text  = jsonParser.getValueAsString();
      System.out.println("Deserializing JSON: " + text);

      if(text == null || text.isEmpty()) {
        return null;
      }


      if(!text.matches("^[0-9a-fA-F]{24}$")) {
        throw new IllegalArgumentException("Input is not a valid ObjectId" + text);
      }

      return new ObjectId(text);
    } catch (IllegalArgumentException e) {
      System.out.println("ObjectIdDeserrializer error: " + jsonParser.getText());
      throw new JsonParseException(jsonParser, "Invalid ObjectId: " + jsonParser.getText(), e);
    }
  }
}