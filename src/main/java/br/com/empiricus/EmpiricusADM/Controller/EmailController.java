package br.com.empiricus.EmpiricusADM.Controller;

import br.com.empiricus.EmpiricusADM.Model.Emails;
import br.com.empiricus.EmpiricusADM.Service.EmailService;
import br.com.empiricus.EmpiricusADM.Service.UserService;
import br.com.empiricus.EmpiricusADM.dto.EmailDTO;
import br.com.empiricus.EmpiricusADM.dto.UserEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/email")
public class EmailController {

    private final EmailService service;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserEmailDTO>> getAll() {
        try {
            List<UserEmailDTO> allEmails = service.findAllEmails();
            return ResponseEntity.ok().body(allEmails);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEmailDTO> getByUser(@PathVariable Long id) {
        try {
            UserEmailDTO emailsByUser = service.findEmailByUser(id);
            return ResponseEntity.ok().body(emailsByUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmailDTO> post(@RequestBody Emails email) {
        try {

            EmailDTO savedEmail = service.createEmail(email);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(savedEmail.getId()).toUri();
            return ResponseEntity.created(uri).body(savedEmail);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.deleteEmail(id);
            return ResponseEntity.accepted().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
