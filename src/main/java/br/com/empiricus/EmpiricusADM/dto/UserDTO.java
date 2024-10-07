package br.com.empiricus.EmpiricusADM.dto;

import br.com.empiricus.EmpiricusADM.Model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String nome;
    private String CPF;
    private Instant data_criacao;
    private Instant data_atualizacao;
    private boolean eh_admin;

    public UserDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.CPF = user.getCpf();
        this.data_criacao = user.getData_criacao();
        this.data_atualizacao = user.getData_atualizacao();
        this.eh_admin = user.isEh_admin();
    }
}
