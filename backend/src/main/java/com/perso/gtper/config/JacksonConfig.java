package com.perso.gtper.config;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.perso.gtper.serialization.ObjectIdDeserrializer;
import com.perso.gtper.serialization.ObjectIdSerializer;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    SimpleModule module = new SimpleModule();

    module.addSerializer(ObjectId.class, new ObjectIdSerializer());
    module.addDeserializer(ObjectId.class, new ObjectIdDeserrializer());

    builder.modules(module);

    return builder;
  }
}