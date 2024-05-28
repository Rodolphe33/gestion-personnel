package com.epsyl.eps.entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.epsyl.eps.custom_serializer.ObjectIdDeserializer;
import com.epsyl.eps.custom_serializer.ObjectIdSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document(collection = "prospects")
public class ProspectEntity {
  @Id
  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserializer.class)
  public String _id;
  public String firstName;
  public String lastName;
  public String trigramme;
  public String email;
  public String phone;
  public String profil;
  public Date dateContact;
  public Date dateEntretien;
  public String statutProspect;

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserializer.class)
  public String bum;

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserializer.class)
  public String rh;

  public String source;
  public double pretentionSalariale;
  public String niveauEtude;
  public String disponibilite;
  public String mobiliteGeo;

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserializer.class)
  public String cv;

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserializer.class)
  public String grille;

  public byte pr;

  @JsonSerialize(using = ObjectIdSerializer.class)
  @JsonDeserialize(using = ObjectIdDeserializer.class)
  public String dc;

  @JsonProperty
  public PushQualifEntity pushQualif;
  @CreatedDate
  public LocalDateTime createdDate;

  public ProspectEntity() {
  }

  public ProspectEntity(String firstName, String lastName, String trigramme, String email, String phone, String profil,
      Date dateContact, Date dateEntretien, String statutProspect, String bum, String rh,
      String source, double pretentionSalariale, String niveauEtude, String disponibilite, String mobiliteGeo,
      String cv, String grille, byte pr, String dc, PushQualifEntity pushQualif, LocalDateTime createdDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.trigramme = trigramme;
    this.email = email;
    this.phone = phone;
    this.profil = profil;
    this.dateContact = dateContact;
    this.dateEntretien = dateEntretien;
    this.statutProspect = statutProspect;
    this.bum = bum;
    this.rh = rh;
    this.source = source;
    this.pretentionSalariale = pretentionSalariale;
    this.niveauEtude = niveauEtude;
    this.disponibilite = disponibilite;
    this.mobiliteGeo = mobiliteGeo;
    this.cv = cv;
    this.grille = grille;
    this.pr = pr;
    this.dc = dc;
    this.pushQualif = pushQualif;
    this.createdDate = createdDate;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getTrigramme() {
    return trigramme;
  }

  public void setTrigramme(String trigramme) {
    this.trigramme = trigramme;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getProfil() {
    return profil;
  }

  public void setProfil(String profil) {
    this.profil = profil;
  }

  public Date getDateContact() {
    return dateContact;
  }

  public void setDateContact(Date dateContact) {
    this.dateContact = dateContact;
  }

  public Date getDateEntretien() {
    return dateEntretien;
  }

  public void setDateEntretien(Date dateEntretien) {
    this.dateEntretien = dateEntretien;
  }

  public String getStatutProspect() {
    return statutProspect;
  }

  public void setStatutProspect(String statutProspect) {
    this.statutProspect = statutProspect;
  }

  public String getBum() {
    return bum;
  }

  public void setBum(String bum) {
    this.bum = bum;
  }

  public String getRh() {
    return rh;
  }

  public void setRh(String rh) {
    this.rh = rh;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public double getPretentionSalariale() {
    return pretentionSalariale;
  }

  public void setPretentionSalariale(double pretentionSalariale) {
    this.pretentionSalariale = pretentionSalariale;
  }

  public String getNiveauEtude() {
    return niveauEtude;
  }

  public void setNiveauEtude(String niveauEtude) {
    this.niveauEtude = niveauEtude;
  }

  public String getDisponibilite() {
    return disponibilite;
  }

  public void setDisponibilite(String disponibilite) {
    this.disponibilite = disponibilite;
  }

  public String getMobiliteGeo() {
    return mobiliteGeo;
  }

  public void setMobiliteGeo(String mobiliteGeo) {
    this.mobiliteGeo = mobiliteGeo;
  }

  public String getCv() {
    return cv;
  }

  public void setCv(String cv) {
    this.cv = cv;
  }

  public String getGrille() {
    return grille;
  }

  public void setGrille(String grille) {
    this.grille = grille;
  }

  public byte getPr() {
    return pr;
  }

  public void setPr(byte pr) {
    this.pr = pr;
  }

  public String getDc() {
    return dc;
  }
  public void setDc(String dc) {
    this.dc = dc;
  }

  public PushQualifEntity getPushQualif() {
    return pushQualif;
  }
  public void setPushQualif(PushQualifEntity pushQualif) {
    this.pushQualif = pushQualif;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String toString() {
    return "Prospect [_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + ", trigramme="
        + trigramme + ", email=" + email + ", phone=" + phone + ", profil=" + profil + ", dateContact="
        + dateContact + ", dateEntretien=" + dateEntretien + ", statutProspect="
        + statutProspect + ", bum=" + bum + ", rh=" + rh + ", source=" + source + ", pretentionSalariale="
        + pretentionSalariale + ", niveauEtude=" + niveauEtude + ", disponibilite=" + disponibilite
        + ", mobiliteGeo=" + mobiliteGeo + ", cv=" + cv + ", grille=" + grille + ", pr=" + pr + ", dc=" + dc
        + ", pushQualif=" + pushQualif + ", createdDate=" + createdDate + "]";
  }

}
