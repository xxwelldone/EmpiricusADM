package br.com.empiricus.EmpiricusADM.Service;


import br.com.empiricus.EmpiricusADM.Model.Emails;
import br.com.empiricus.EmpiricusADM.Repository.EmailRepository;
import br.com.empiricus.EmpiricusADM.Model.Exceptions.ItemNotFound;
import br.com.empiricus.EmpiricusADM.dto.EmailDTO;
import br.com.empiricus.EmpiricusADM.dto.UserDTO;

import br.com.empiricus.EmpiricusADM.dto.UserEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository repo;
    private final MailService mailService;


    public List<UserEmailDTO> findAllEmails() {
        List<Emails> allEmails = repo.findAll();
       List<UserEmailDTO> allUserEmailDto =  allEmails.stream().map(email -> findEmailByUser(email.getUsuario().getId())).toList();
        return allUserEmailDto.stream().distinct().toList();
    }

    public UserEmailDTO findEmailByUser(Long userId) {
        List<Emails> emailList = repo.findAll().stream().filter(email -> Objects.equals(email.getUsuario().getId(), userId)).toList();
        UserEmailDTO userEmailDTO = new UserEmailDTO(new UserDTO(emailList.get(0).getUsuario()));
        emailList.forEach(userEmail -> userEmailDTO.addEmails(new EmailDTO(userEmail)));
        return userEmailDTO;
    }

    public EmailDTO createEmail(Emails email) {

        Emails savedEmail = repo.save(email);
        notifyAdm("criado", savedEmail.getEmail(), savedEmail.getUsuario().getCpf());
        return new EmailDTO(savedEmail);
    }

    public void deleteEmail(Long id) {
        Emails emailToBeDeleted = repo.findById(id).orElseThrow(() -> new ItemNotFound("Email exception id: " + id));
        repo.deleteById(id);
        notifyAdm("deletado", emailToBeDeleted.getEmail(), emailToBeDeleted.getUsuario().getCpf());
    }

    private void notifyAdm(String action, String emailAltered, String cpfAltered) {

        List<Emails> emailsAdm = repo.findAll().stream().filter(emails -> emails.getUsuario().isEh_admin()).toList();
        String subject = String.format("O email %s foi %s para o usuário de CPF %s", emailAltered, action, cpfAltered);
        String message = String.format("Essa é uma notificação do sistema Empiricus ADM para informar que um novo usuário foi %s", action);
        emailsAdm.forEach(email -> mailService.sendEmailTxt(email.getEmail(), subject, message));


    }

}
