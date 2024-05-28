package com.epsyl.eps.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.epsyl.eps.entities.ProspectEntity;
import com.epsyl.eps.entities.PushQualifEntity;

public class ProspectDTO {
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
    public String bum;
    public String rh;
    public String source;
    public double pretentionSalariale;
    public String niveauEtude;
    public String disponibilite;
    public String mobiliteGeo;
    public String cv;
    public String grille;
    public byte pr;
    public String dc;
    public PushQualifEntity pushQualif;@CreatedDate
    public LocalDateTime createdDate;

    public ProspectDTO() {}

    public ProspectDTO(ProspectEntity prospect, String bum, String rh, String cv, String grille, String dc) {
        this._id = prospect.get_id();
        this.firstName = prospect.getFirstName();
        this.lastName = prospect.getLastName();
        this.trigramme = prospect.getTrigramme();
        this.email = prospect.getEmail();
        this.phone = prospect.getPhone();
        this.profil = prospect.getProfil();
        this.dateContact = prospect.getDateContact();
        this.dateEntretien = prospect.getDateEntretien();
        this.statutProspect = prospect.getStatutProspect();
        this.bum = bum;
        this.rh = rh;
        this.source = prospect.getSource();
        this.pretentionSalariale = prospect.getPretentionSalariale();
        this.niveauEtude = prospect.getNiveauEtude();
        this.disponibilite = prospect.getDisponibilite();
        this.mobiliteGeo = prospect.getMobiliteGeo();
        this.cv = cv;
        this.grille = grille;
        this.pr = prospect.getPr();
        this.dc = dc;
        this.pushQualif = prospect.getPushQualif();
        this.createdDate = prospect.getCreatedDate();
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
}
