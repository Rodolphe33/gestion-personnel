package com.epsyl.eps.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.epsyl.eps.enums.ERole;

@Document(collection = "roles")
public class RoleEntity {
  @Id
  private String _id;
  private ERole name;

  public RoleEntity() {}

  public RoleEntity(ERole name) {
    this.name = name;
  }

  public String getId() {
    return _id;
  }

  public void setId(String _id) {
    this._id = _id;
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {
    this.name = name;
  }

  
}
