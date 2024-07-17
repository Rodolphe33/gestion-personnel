package com.perso.gtper.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.perso.gtper.emailings.MailGrilleAdded;
import com.perso.gtper.entities.Personnal;
import com.perso.gtper.repositories.PersonnalRepository;

@ExtendWith(MockitoExtension.class)
public class PersonnalServiceTest {
  @Mock
  private PersonnalRepository personnalRepository;

  @Mock
  private MailGrilleAdded mailGrilleAdded;

  @InjectMocks
  private PersonnalService personnalService;

  private Personnal personnal;

  @BeforeEach
  public void setUp() {
    personnal = new Personnal();
    personnal.set_id(new ObjectId());
    personnal.setFirstName("John");
    personnal.setLastName("Doe");
    personnal.setEmail("john.doe@example.com");
    personnal.setTrigramme("JDO");
    personnal.setBum(new ObjectId()); // Exemple de création d'un ObjectId
    personnal.setRh(new ObjectId());  // Exemple de création d'un ObjectId
    personnal.setPhone("0011223344");
    personnal.setProfil("profil");
    personnal.setContactDate(new Date());
    personnal.setMeetingDate(new Date());
    personnal.setSource("linkdin");
    personnal.setPretentionSalarial("50-75");
    personnal.setNiveauEtude("bb");
    personnal.setFrenchNationality(true);
    personnal.setNationality(null);
    personnal.setExperience("BB");
    personnal.setEnglishLevel("novice");
    personnal.setAvis("TU");
    personnal.setDisponibilite("asap");
    personnal.setMobiliteGeo(null);
    personnal.setCv(new ObjectId());
    personnal.setGrille(new ObjectId());
    personnal.setPr(50);
    personnal.setPrValidated(true);
    personnal.setDc(new ObjectId());
    personnal.setPushQualif(null);
    personnal.setCreatedDate(null);
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAllPersonnals() {
    when(personnalRepository.findAll()).thenReturn(Arrays.asList(personnal));

    List<Personnal> personnals = personnalService.allPersonnals();

    assertThat(personnals).isNotNull();
    assertThat(personnals.size()).isEqualTo(1);
    assertThat(personnals.get(0)).isEqualTo(personnal);
  }

  @Test
  public void testGetPersonnalByID() {
    when(personnalRepository.findById(personnal.get_id())).thenReturn(Optional.of(personnal));

    Optional<Personnal> foundPersonnal = personnalService.getPersonnalByID(personnal.get_id());

    assertThat(foundPersonnal).isPresent();
    assertThat(foundPersonnal.get()).isEqualTo(personnal);
  }

  @Test
  public void testSave() {
    when(personnalRepository.save(any(Personnal.class))).thenReturn(personnal);

    Personnal savedPersonnal = personnalService.createPersonnal(personnal);

    assertThat(savedPersonnal).isNotNull();
    assertThat(savedPersonnal).isEqualTo(personnal);
  }

  @Test
  public void testUpdatePersonnal() {
    // Mock data
    ObjectId personnalId = new ObjectId("667ae5ad6aa2bc3166214017");
    ObjectId personnalGrille = new ObjectId("667ae5ad6aa2bc3166215018");
    Personnal existingPersonnal = new Personnal();
    existingPersonnal.set_id(personnalId);

    Personnal updatedPersonnal = new Personnal();
    updatedPersonnal.set_id(personnalId);
    updatedPersonnal.setFirstName("Updated John");
    updatedPersonnal.setGrille(personnalGrille);

    when((personnalRepository.findById(personnalId))).thenReturn(Optional.of(existingPersonnal));    
    when(personnalRepository.save(any(Personnal.class))).thenReturn(updatedPersonnal);

    // Personnal result = personnalService.updatePersonnal(personnalId, updatedPersonnal);
    
    // verify(mailGrilleAdded, times(1)).notifyGrilleAdded(result);
  }

  @Test
  public void testDeletePersonnalById() {
    // Mock data
    ObjectId personnalId = new ObjectId("667ae5ad6aa2bc3166214017");
    Personnal personnalToDelete = new Personnal();
    personnalToDelete.set_id(personnalId);

    when(personnalRepository.findById(personnalId)).thenReturn(Optional.of(personnalToDelete));

    personnalService.deletePersonnalById(personnalId);

    verify(personnalRepository, times(1)).deleteById(personnalId);
  }

  @Test
  public void testTrigramExist() {
    when(personnalRepository.existsByTrigramme("JDO")).thenReturn(true);

    boolean result = personnalService.trigramExist("JDO");

    assertThat(result).isTrue();
  }
}
