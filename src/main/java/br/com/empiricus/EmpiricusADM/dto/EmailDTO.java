package br.com.empiricus.EmpiricusADM.dto;

import br.com.empiricus.EmpiricusADM.Model.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
@Data
@NoArgsConstructor
public class EmailDTO {
    private Long id;
    private String email;
    private Instant data_criacao;
    private Instant data_atualizacao;
    private Long userId;

    public EmailDTO(Email email) {
        this.id = email.getId();
        this.email = email.getEmail();
        this.data_criacao = email.getData_criacao();
        this.data_atualizacao = email.getData_atualizacao();
        this.userId = email.getUsuario().getId();
    }
}
