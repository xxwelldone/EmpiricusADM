package br.com.empiricus.EmpiricusADM.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    @Value("{spring.mail.username}")
    private String from;

    public String sendEmailTxt(String recipient, String subject, String msg) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(recipient);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(msg);
            mailSender.send(simpleMailMessage);
            return String.format("Email sent to %s", recipient);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
