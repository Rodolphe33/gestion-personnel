package com.perso.gtper.emailings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perso.gtper.entities.FileStorage;
import com.perso.gtper.entities.Personnal;
import com.perso.gtper.entities.User;
import com.perso.gtper.repositories.UserRepository;
import com.perso.gtper.services.FileStorageService;
import com.perso.gtper.services.email.EmailService;

@Component
public class MailGrilleAdded {

  @Autowired
  private EmailService emailService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FileStorageService fileStorageService;

  String[] recipients = {
    "rodolphe.lassalle33@gmail.com",
  };

  public void notifyGrilleAdded(Personnal personnal) throws Exception {
    User rh = userRepository.findBy_id(personnal.rh);
    String fullname = rh.getFirstName().substring(0,1) + "." + rh.getLastName();
    String nationality = personnal.getFrenchNationality() ? "Française" : personnal.getNationality();
    String mobility = String.join(", ", personnal.getMobiliteGeo());

    String subject = "CV + Grille_" + personnal.getFirstName() + " " + (personnal.getLastName()).toUpperCase();
    String text = 
      "<html>" +
        "<body>" +
          "<p>Bonjour,</p>" +
          "<ol>" +
            "<li>" +
              "<span>Nom : </span><span style='color:red;'>" + personnal.getFirstName() + " " + personnal.getLastName() + "</span>" + 
            "</li>" +
            "<li>" +
              "<span>Diplôme + compétences : </span><span style='color:red;'>" + personnal.getNiveauEtude() + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Nationalité : </span><span style='color:red;'>" + nationality + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Année d'expérience : </span><span style='color:red;'>" + personnal.getExperience() + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Prétention salariale : </span><span style='color:red;'>" + personnal.getPretentionSalarial() + "K</span>" +
            "</li>" +
            "<li>" +
              "<span>Mobilité géographique : </span><span style='color:red;'>" + mobility + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Anglais : </span><span style='color:red;'>" + personnal.getEnglishLevel() + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Disponibilité : </span><span style='color:red;'>" + personnal.getDisponibilite() + "</span>" +
            "</li>" +
          "</ol>" +
          "<span>Avis : </span>" +
          "<p>" + personnal.getAvis() + "</p>" +
          "<br>" +
          "<p>Cordialement,</p>" +
          "<p>" + fullname + "</p>" +
        "</body>" +
      "</html>";

    FileStorage cvFile = fileStorageService.getFile(personnal.getCv());
    FileStorage grilleFile = fileStorageService.getFile(personnal.getGrille());
    
    FileStorage[] files = {cvFile, grilleFile};

    for (String recipient : recipients) {
      emailService.sendEmailWithAttachments(recipient, subject, text, files);
    }
  }
}