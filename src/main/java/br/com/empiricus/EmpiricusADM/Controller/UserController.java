package br.com.empiricus.EmpiricusADM.Controller;

import br.com.empiricus.EmpiricusADM.Model.User;
import br.com.empiricus.EmpiricusADM.Service.UserService;
import br.com.empiricus.EmpiricusADM.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        try {
            List<UserDTO> allUsers = service.findAllUsers();
            return ResponseEntity.ok().body(allUsers);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        try {
            UserDTO userById = service.findUserById(id);
            return ResponseEntity.ok().body(userById);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<UserDTO> post(@RequestBody User user) {
        UserDTO savedUser = service.createUser(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}").
                buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> put(@PathVariable Long id, @RequestBody User user) {
        try {
            UserDTO updatedUser = service.updateUser(id, user);
            return ResponseEntity.accepted().body(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
        service.deleteUser(id);
        return ResponseEntity.accepted().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
