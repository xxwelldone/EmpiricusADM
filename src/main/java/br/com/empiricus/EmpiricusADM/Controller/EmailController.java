package br.com.empiricus.EmpiricusADM.Controller;

import br.com.empiricus.EmpiricusADM.Model.Email;
import br.com.empiricus.EmpiricusADM.Service.EmailService;
import br.com.empiricus.EmpiricusADM.dto.EmailDTO;
import br.com.empiricus.EmpiricusADM.dto.UserEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "email")
public class EmailController {

    private final EmailService service;

    @GetMapping
    public ResponseEntity<List<EmailDTO>> getAll() {
        try {
            List<EmailDTO> allEmails = service.findAllEmails();
            return ResponseEntity.ok().body(allEmails);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEmailDTO> getByUser(@PathVariable Long id) {
        try {
            UserEmailDTO emailsByUser = service.findEmailByUser(id);
            return ResponseEntity.ok().body(emailsByUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmailDTO> post(@RequestBody Email email) {
        try {
            EmailDTO savedEmail = service.createEmail(email);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(savedEmail.getId()).toUri();
            return ResponseEntity.created(uri).body(savedEmail);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

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
