package com.epsyl.eps.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.epsyl.eps.entities.FileStorage;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String to, String subject, String htmlContent) throws Exception {
    MimeMessage mimeMessage = mailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(htmlContent, true);

      mailSender.send(mimeMessage);
    } catch (MessagingException me) {
      me.printStackTrace();
    }
  }

  public void sendEmailWithAttachments(String to, String subject, String htmlContent, FileStorage[] attachments) throws Exception {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(htmlContent, true);
      // Ajout de piece jointe
      for(FileStorage file : attachments) {
        helper.addAttachment(file.filename, new ByteArrayResource(file.data));
      }

      mailSender.send(mimeMessage);
    } catch (MessagingException me) {
      me.printStackTrace();
    }
  }
}