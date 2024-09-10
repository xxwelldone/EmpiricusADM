package br.com.empiricus.EmpiricusADM.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class UserEmailDTO {
    UserDTO user;
    List<EmailDTO> emailDTOList = new ArrayList<>();

    public UserEmailDTO(UserDTO user) {
        this.user = user;
    }
    public void addEmails(EmailDTO emailDTO){
        emailDTOList.add(emailDTO);
    }
}
