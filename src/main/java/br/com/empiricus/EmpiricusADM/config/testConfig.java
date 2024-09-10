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
            Instant.now(), true);
    Email email1 = new Email(1L, "wesley@empiricus.com", Instant.now(), Instant.now(), user1);
    Email email2 = new Email(2L, "wesley@vitreo.com", Instant.now(), Instant.now(), user1);
    Email email3 = new Email(3L, "wesley@empiricus.edu.com", Instant.now(), Instant.now(), user1);

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(user1);
        emailService.createEmail(email1);
        emailService.createEmail(email2);
        emailService.createEmail(email3);

    }
}
