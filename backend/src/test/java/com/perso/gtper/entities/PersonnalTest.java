package com.perso.gtper.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;


public class PersonnalTest {
  @Test
  public void testPersonnalBuilder() {
    // Test de la méthode builder() de la classe Personnal.
    ObjectId bumId = new ObjectId("667bc2ab8113383a0670eaef");
    ObjectId rhId = new ObjectId("667bc2ab8113383a0670eaf0");
    ObjectId cvId = new ObjectId("667bc2ab8113383a0670eaf1");
    ObjectId grilleId = new ObjectId("667bc2ab8113383a0670eaf2");
    ObjectId dcId = new ObjectId("667bc2ab8113383a0670eaf3");

    Personnal personnal1 = Personnal.builder()
            .firstName("John")
            .lastName("Doe")
            .email("john.doe@example.com")
            .trigramme("JDO")
            .bum(bumId) // Exemple de création d'un ObjectId
            .rh(rhId)  // Exemple de création d'un ObjectId
            .phone("0011223344")
            .profil("profil")
            .contactDate(new Date())
            .meetingDate(new Date())
            .source("linkdin")
            .pretentionSalarial("50-75")
            .niveauEtude("bb")
            .frenchNationality(true)
            .nationality(null)
            .experience("BB")
            .englishLevel("novice")
            .avis("TU")
            .disponibilite("asap")
            .mobiliteGeo(null)
            .cv(cvId)
            .grille(grilleId)
            .pr(50)
            .prValidated(true)
            .dc(dcId)
            .pushQualif(null)
            .createdDate(null)
            .build();

    Personnal personnal2 = Personnal.builder()
            .firstName("John")
            .lastName("Doe")
            .email("john.doe@example.com")
            .trigramme("JDO")
            .bum(bumId) // Exemple de création d'un ObjectId
            .rh(rhId)  // Exemple de création d'un ObjectId
            .phone("0011223344")
            .profil("profil")
            .contactDate(new Date())
            .meetingDate(new Date())
            .source("linkdin")
            .pretentionSalarial("50-75")
            .niveauEtude("bb")
            .frenchNationality(true)
            .nationality(null)
            .experience("BB")
            .englishLevel("novice")
            .avis("TU")
            .disponibilite("asap")
            .mobiliteGeo(null)
            .cv(cvId)
            .grille(grilleId)
            .pr(50)
            .prValidated(true)
            .dc(dcId)
            .pushQualif(null)
            .createdDate(null)
            .build();

    assertThat(personnal1).isEqualTo(personnal2);
  }
}
