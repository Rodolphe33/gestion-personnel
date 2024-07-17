package com.perso.gtper.emailings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perso.gtper.entities.Personnal;
import com.perso.gtper.entities.User;
import com.perso.gtper.repositories.UserRepository;
import com.perso.gtper.services.email.EmailService;

@Component
public class MailBumAdded {

  @Autowired
  private EmailService emailService;

  @Autowired
  private UserRepository userRepository;

  String[] recipients = {
    "rodolphe.lassalle33@gmail.com"
  };

  public void notifyBumAdded(Personnal personnal) {
    System.out.println("--------------------------------");
    System.out.println("MailBumAdded=> " + personnal);
    System.out.println("--------------------------------");

    User bum = userRepository.findBy_id(personnal.bum);
    String fullname = bum.getFirstName().substring(0,1) + "." + bum.getLastName();

    String subject = "GO pour " + personnal.getFirstName() + "_" + personnal.getLastName().toUpperCase();
    String text = 
      "<html>" +
        "<body>" +
          "<p>Bonjour,</p>" +
          "<br>" +
          "<span>" + bum.getFirstName() + " " + bum.getLastName() + " propose " + personnal.pr + "K, pour </span>" +
          personnal.getLastName().toUpperCase() + " " + personnal.getFirstName().toUpperCase() +
          "<br>" +
          "<p>Cordialement,</p>" +
          "<p>" + fullname + "</p>" +
        "</body>" +
      "</html>";

    for (String recipient : recipients) {
      try {
      emailService.sendEmail(recipient, subject, text);
      } catch (Exception exception) {
        System.err.println("Erreur lors de l'envoi de l'email : " + exception.getMessage());
      }
    }
  }
}