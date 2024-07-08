package com.epsyl.eps.emailings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsyl.eps.entities.FileStorage;
import com.epsyl.eps.entities.Prospect;
import com.epsyl.eps.entities.User;
import com.epsyl.eps.repositories.UserRepository;
import com.epsyl.eps.services.FileStorageService;
import com.epsyl.eps.services.email.EmailService;

@Component
public class MailGrilleAdded {

  @Autowired
  private EmailService emailService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FileStorageService fileStorageService;

  String[] recipients = {
    "bum-bdx@epsyl-alcen.com",
    "rhbordeaux@epsyl-alcen.com",
    "rhidf@epsyl-alcen.com",
    "service-rh@epsyl-alcen.com",
  };

  String[] recipientsTest = {
    "rlassalle@epsyl-alcen.com", 
    "mjpereira@epsyl-alcen.com",
    "bmortier@epsyl-alcen.com"
  };

  public void notifyGrilleAdded(Prospect prospect) throws Exception {
    User rh = userRepository.findBy_id(prospect.rh);
    String fullname = rh.getFirstName().substring(0,1) + "." + rh.getLastName();
    String nationality = prospect.getFrenchNationality() ? "Française" : prospect.getNationality();
    String mobility = String.join(", ", prospect.getMobiliteGeo());

    String subject = "CV + Grille_" + prospect.getFirstName() + " " + (prospect.getLastName()).toUpperCase();
    String text = 
      "<html>" +
        "<body>" +
          "<p>Bonjour,</p>" +
          "<ol>" +
            "<li>" +
              "<span>Nom : </span><span style='color:red;'>" + prospect.getFirstName() + " " + prospect.getLastName() + "</span>" + 
            "</li>" +
            "<li>" +
              "<span>Diplôme + compétences : </span><span style='color:red;'>" + prospect.getNiveauEtude() + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Nationalité : </span><span style='color:red;'>" + nationality + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Année d'expérience : </span><span style='color:red;'>" + prospect.getExperience() + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Prétention salariale : </span><span style='color:red;'>" + prospect.getPretentionSalarial() + "K</span>" +
            "</li>" +
            "<li>" +
              "<span>Mobilité géographique : </span><span style='color:red;'>" + mobility + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Anglais : </span><span style='color:red;'>" + prospect.getEnglishLevel() + "</span>" +
            "</li>" +
            "<li>" +
              "<span>Disponibilité : </span><span style='color:red;'>" + prospect.getDisponibilite() + "</span>" +
            "</li>" +
          "</ol>" +
          "<span>Avis : </span>" +
          "<p>" + prospect.getAvis() + "</p>" +
          "<br>" +
          "<p>Cordialement,</p>" +
          "<p>" + fullname + "</p>" +
        "</body>" +
      "</html>";

    FileStorage cvFile = fileStorageService.getFile(prospect.getCv());
    FileStorage grilleFile = fileStorageService.getFile(prospect.getGrille());
    
    FileStorage[] files = {cvFile, grilleFile};

    // TODO Décommenter pour la prod
    // for (String recipient : recipients) {
    //   sendEmail(recipient, subject, text);
    // }

    for (String rTest : recipientsTest) {
      emailService.sendEmailWithAttachments(rTest, subject, text, files);
    }
  }
}