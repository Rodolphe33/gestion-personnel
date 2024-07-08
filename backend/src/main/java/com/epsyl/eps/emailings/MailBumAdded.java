package com.epsyl.eps.emailings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epsyl.eps.entities.Prospect;
import com.epsyl.eps.entities.User;
import com.epsyl.eps.repositories.UserRepository;
import com.epsyl.eps.services.email.EmailService;

@Component
public class MailBumAdded {

  @Autowired
  private EmailService emailService;

  @Autowired
  private UserRepository userRepository;

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

  public void notifyBumAdded(Prospect prospect) {
    System.out.println("--------------------------------");
    System.out.println("MailBumAdded=> " + prospect);
    System.out.println("--------------------------------");

    User bum = userRepository.findBy_id(prospect.bum);
    String fullname = bum.getFirstName().substring(0,1) + "." + bum.getLastName();

    String subject = "GO pour " + prospect.getFirstName() + "_" + prospect.getLastName().toUpperCase();
    String text = 
      "<html>" +
        "<body>" +
          "<p>Bonjour,</p>" +
          "<br>" +
          "<span>" + bum.getFirstName() + " " + bum.getLastName() + " propose " + prospect.pr + "K, pour </span>" +
          prospect.getLastName().toUpperCase() + " " + prospect.getFirstName().toUpperCase() +
          "<br>" +
          "<p>Cordialement,</p>" +
          "<p>" + fullname + "</p>" +
        "</body>" +
      "</html>";

    // TODO Décommenter pour la prod
    // for (String recipient : recipients) {
    //   sendEmail(recipient, subject, text);
    // }

    for (String rTest : recipientsTest) {
      try {
        emailService.sendEmail(rTest, subject, text);
      } catch (Exception exception) {
        System.err.println("Erreur lors de l'envoi de l'email : " + exception.getMessage());
      }
    }
  }
}