package br.com.empiricus.EmpiricusADM.Model;

import br.com.empiricus.EmpiricusADM.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 100, message = "Invalid name")
    private String nome;
    @NotBlank
    @CPF(message = "Invalid CPF or Invalid format")
    private String cpf;
    @NotBlank
    @Length(min = 6, message = "Invalid password")
    private String password;
    private Instant data_criacao;
    private Instant data_atualizacao;
    private boolean eh_admin;

}


