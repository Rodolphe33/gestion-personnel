package com.epsyl.eps.config;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epsyl.eps.serialization.ObjectIdDeserrializer;
import com.epsyl.eps.serialization.ObjectIdSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class ObjectMapperConfig {
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();

    module.addSerializer(ObjectId.class, new ObjectIdSerializer());
    module.addDeserializer(ObjectId.class, new ObjectIdDeserrializer());
    mapper.registerModule(module);
    return mapper;
  }
}