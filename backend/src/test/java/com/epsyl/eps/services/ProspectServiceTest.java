package com.epsyl.eps.services;

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

import com.epsyl.eps.emailings.MailGrilleAdded;
import com.epsyl.eps.entities.Prospect;
import com.epsyl.eps.repositories.ProspectRepository;

@ExtendWith(MockitoExtension.class)
public class ProspectServiceTest {
  @Mock
  private ProspectRepository prospectRepository;

  @Mock
  private MailGrilleAdded mailGrilleAdded;

  @InjectMocks
  private ProspectService prospectService;

  private Prospect prospect;

  @BeforeEach
  public void setUp() {
    prospect = new Prospect();
    prospect.set_id(new ObjectId());
    prospect.setFirstName("John");
    prospect.setLastName("Doe");
    prospect.setEmail("john.doe@example.com");
    prospect.setTrigramme("JDO");
    prospect.setBum(new ObjectId()); // Exemple de création d'un ObjectId
    prospect.setRh(new ObjectId());  // Exemple de création d'un ObjectId
    prospect.setPhone("0011223344");
    prospect.setProfil("profil");
    prospect.setContactDate(new Date());
    prospect.setMeetingDate(new Date());
    prospect.setSource("linkdin");
    prospect.setPretentionSalarial("50-75");
    prospect.setNiveauEtude("bb");
    prospect.setFrenchNationality(true);
    prospect.setNationality(null);
    prospect.setExperience("BB");
    prospect.setEnglishLevel("novice");
    prospect.setAvis("TU");
    prospect.setDisponibilite("asap");
    prospect.setMobiliteGeo(null);
    prospect.setCv(new ObjectId());
    prospect.setGrille(new ObjectId());
    prospect.setPr(50);
    prospect.setPrValidated(true);
    prospect.setDc(new ObjectId());
    prospect.setPushQualif(null);
    prospect.setCreatedDate(null);
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAllProspects() {
    when(prospectRepository.findAll()).thenReturn(Arrays.asList(prospect));

    List<Prospect> prospects = prospectService.allProspects();

    assertThat(prospects).isNotNull();
    assertThat(prospects.size()).isEqualTo(1);
    assertThat(prospects.get(0)).isEqualTo(prospect);
  }

  @Test
  public void testGetProspectByID() {
    when(prospectRepository.findById(prospect.get_id())).thenReturn(Optional.of(prospect));

    Optional<Prospect> foundProspect = prospectService.getProspectByID(prospect.get_id());

    assertThat(foundProspect).isPresent();
    assertThat(foundProspect.get()).isEqualTo(prospect);
  }

  @Test
  public void testSave() {
    when(prospectRepository.save(any(Prospect.class))).thenReturn(prospect);

    Prospect savedProspect = prospectService.createProspect(prospect);

    assertThat(savedProspect).isNotNull();
    assertThat(savedProspect).isEqualTo(prospect);
  }

  @Test
  public void testUpdateProspect() {
    // Mock data
    ObjectId prospectId = new ObjectId("667ae5ad6aa2bc3166214017");
    ObjectId prospectGrille = new ObjectId("667ae5ad6aa2bc3166215018");
    Prospect existingProspect = new Prospect();
    existingProspect.set_id(prospectId);

    Prospect updatedProspect = new Prospect();
    updatedProspect.set_id(prospectId);
    updatedProspect.setFirstName("Updated John");
    updatedProspect.setGrille(prospectGrille);

    when((prospectRepository.findById(prospectId))).thenReturn(Optional.of(existingProspect));    
    when(prospectRepository.save(any(Prospect.class))).thenReturn(updatedProspect);

    // Prospect result = prospectService.updateProspect(prospectId, updatedProspect);
    
    // verify(mailGrilleAdded, times(1)).notifyGrilleAdded(result);
  }

  @Test
  public void testDeleteProspectById() {
    // Mock data
    ObjectId prospectId = new ObjectId("667ae5ad6aa2bc3166214017");
    Prospect prospectToDelete = new Prospect();
    prospectToDelete.set_id(prospectId);

    when(prospectRepository.findById(prospectId)).thenReturn(Optional.of(prospectToDelete));

    prospectService.deleteProspectById(prospectId);

    verify(prospectRepository, times(1)).deleteById(prospectId);
  }

  @Test
  public void testTrigramExist() {
    when(prospectRepository.existsByTrigramme("JDO")).thenReturn(true);

    boolean result = prospectService.trigramExist("JDO");

    assertThat(result).isTrue();
  }
}
