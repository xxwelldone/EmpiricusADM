package br.com.empiricus.EmpiricusADM.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String password;
    private Instant data_criacao;
    private Instant data_atualizacao;
    private boolean eh_admin;
    @OneToMany(mappedBy = "usuario")
    private List<Email> emails;
}
