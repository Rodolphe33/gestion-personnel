package com.perso.gtper.serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.perso.gtper.entities.Role;
import com.perso.gtper.entities.User;
import com.perso.gtper.enums.ERole;

public class UserDeserializer extends JsonDeserializer<User> {
  @Override
  public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    ObjectMapper mapper = (ObjectMapper) p.getCodec();
    ObjectNode node = mapper.readTree(p);

    User user = new User();
    user.set_id(new ObjectId(node.get("_id").asText()));
    user.setFirstName(node.get("firstName").asText());
    user.setLastName(node.get("lastName").asText());
    user.setEmail(node.get("email").asText());
    user.setPassword(node.get("password").asText());

    List<Role> roles = new ArrayList<>();
    node.get("roles").forEach(roleNode -> {
      Role role = new Role();
      role.setName(ERole.valueOf(roleNode.get("name").asText().toUpperCase()));
      roles.add(role);
    });
    user.setRoles(roles);

    return user;
  }
}