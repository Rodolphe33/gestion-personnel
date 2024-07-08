package com.epsyl.eps.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epsyl.eps.serialization.GrantedAuthorityDeserializer;
import com.epsyl.eps.serialization.GrantedAuthoritySerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {
  @Id
  private ObjectId _id;

  @NotBlank
  @Size(max = 20)
  private String firstName;

  @NotBlank
  @Size(max = 20)
  private String lastName;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @DBRef
  private Collection<Role> roles;

  @JsonSerialize(contentUsing = GrantedAuthoritySerializer.class)
  @JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName().toString())));
    return authorities;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}