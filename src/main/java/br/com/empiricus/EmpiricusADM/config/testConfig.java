package br.com.empiricus.EmpiricusADM.config;

import br.com.empiricus.EmpiricusADM.Model.Emails;
import br.com.empiricus.EmpiricusADM.Model.User;
import br.com.empiricus.EmpiricusADM.Service.EmailService;
import br.com.empiricus.EmpiricusADM.Service.UserService;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Set;


@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class testConfig implements CommandLineRunner {
    private final UserService userService;
    private final EmailService emailService;


    User user1 = new User(1L, "Wesley M", "123.456.789-09", "abcdefg1010", Instant.now(),
            Instant.now(), true);
    User user2 = new User(2L, "Felipe Yukio", "111.444.777-35", "abcdefg1010", Instant.now(),
            Instant.now(), false);
    Emails email1 = new Emails(1L, "wesley@empiricus.com", Instant.now(), Instant.now(), user1);
    Emails email2 = new Emails(2L, "wesley@vitreo.com", Instant.now(), Instant.now(), user1);
    Emails email3 = new Emails(3L, "wesley@empiricus.com", Instant.now(), Instant.now(), user1);

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(user1);
        userService.createUser(user2);
        emailService.createEmail(email1);
        emailService.createEmail(email2);
        emailService.createEmail(email3);
//       ValidatorFactory validatorFactory =  Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//       Set<ConstraintViolation<User>> violations= validator.validate(user1);
//       for (ConstraintViolation<User> violation: violations){
//           System.out.println(violation);
//       }
    }
}
