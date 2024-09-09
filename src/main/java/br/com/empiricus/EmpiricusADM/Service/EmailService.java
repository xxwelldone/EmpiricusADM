package br.com.empiricus.EmpiricusADM.Service;

import br.com.empiricus.EmpiricusADM.Model.Email;
import br.com.empiricus.EmpiricusADM.Repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository repo;

    public List<Email> findAllEmails(){
        return repo.findAll();
    }
    public List<Email> findEmailbyUser(String username){
        return repo.findAllByUsuarioNomeContainingIgnoreCase(username);
    }
    public Email createEmail(Email email){
       return  repo.save(email);
    }
    public void DeleteEmail(Long id){
        repo.deleteById(id);
    }

}
