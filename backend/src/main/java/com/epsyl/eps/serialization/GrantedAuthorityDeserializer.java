package com.epsyl.eps.serialization;

import java.io.IOException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class GrantedAuthorityDeserializer extends StdDeserializer<GrantedAuthority> {
  public GrantedAuthorityDeserializer() {
    super(GrantedAuthority.class);
  }

  @Override
  public GrantedAuthority deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    return new SimpleGrantedAuthority(p.getValueAsString());
  }
}