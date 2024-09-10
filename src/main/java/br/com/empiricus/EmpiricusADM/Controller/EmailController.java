package br.com.empiricus.EmpiricusADM.Controller;

import br.com.empiricus.EmpiricusADM.Model.Email;
import br.com.empiricus.EmpiricusADM.Service.EmailService;
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
    public ResponseEntity<List<Email>> getAll() {
        try {
            List<Email> allEmails = service.findAllEmails();
            return ResponseEntity.ok().body(allEmails);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Email>> getByUser(@PathVariable String username) {
        try {
            List<Email> emailsByUser = service.findEmailbyUser(username);
            return ResponseEntity.ok().body(emailsByUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Email> post(@RequestBody Email email) {
        try {
            Email savedEmail = service.createEmail(email);
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
            service.DeleteEmail(id);
            return ResponseEntity.accepted().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
