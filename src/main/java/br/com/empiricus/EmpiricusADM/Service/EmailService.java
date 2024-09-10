package br.com.empiricus.EmpiricusADM.Service;

import br.com.empiricus.EmpiricusADM.Model.Email;
import br.com.empiricus.EmpiricusADM.Repository.EmailRepository;
import br.com.empiricus.EmpiricusADM.dto.EmailDTO;
import br.com.empiricus.EmpiricusADM.dto.UserDTO;

import br.com.empiricus.EmpiricusADM.dto.UserEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository repo;
    private final MailService mailService;


    public List<EmailDTO> findAllEmails() {

        return repo.findAll().stream().map(EmailDTO::new).toList();
    }

    public UserEmailDTO findEmailByUser(Long userId) {
        List<Email> emailList = repo.findAll().stream().filter(email -> Objects.equals(email.getUsuario().getId(), userId)).toList();
        UserEmailDTO userEmailDTO =  new UserEmailDTO(new UserDTO(emailList.get(0).getUsuario()));
        emailList.forEach(userEmail -> userEmailDTO.addEmails(new EmailDTO(userEmail)));
        return userEmailDTO;
    }

    public EmailDTO createEmail(Email email) {
        return new EmailDTO(repo.save(email));
    }

    public void deleteEmail(Long id) {
        repo.deleteById(id);
    }

}
