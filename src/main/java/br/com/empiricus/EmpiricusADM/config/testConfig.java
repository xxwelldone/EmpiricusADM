package br.com.empiricus.EmpiricusADM.config;

import br.com.empiricus.EmpiricusADM.Model.Email;
import br.com.empiricus.EmpiricusADM.Model.User;
import br.com.empiricus.EmpiricusADM.Service.EmailService;
import br.com.empiricus.EmpiricusADM.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class testConfig implements CommandLineRunner {
    private final UserService userService;
    private final EmailService emailService;


    User user1 = new User(1L, "Wesley M", "32794274", "abc123", Instant.now(),
            Instant.now(), false);
    Email email1 = new Email(1L, "teste@gmail.com", Instant.now(), Instant.now(), user1);
    Email email2 = new Email(2L, "endrigo.guilherme@hotmail.com", Instant.now(), Instant.now(), user1);
    Email email3 = new Email(3L, "welldone@gmail.com", Instant.now(), Instant.now(), user1);
    User user2 = new User(2L, "Amanda M", "738278424", "abc123", Instant.now(),
            Instant.now(), true);
    Email email4 = new Email(4L, "wesleymenezesdnovaes@gmail.com", Instant.now(), Instant.now(), user2);

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(user1);
        emailService.createEmail(email1);
        emailService.createEmail(email2);
        emailService.createEmail(email3);
        userService.createUser(user2);
        emailService.createEmail(email4);

    }
}
