package br.com.empiricus.EmpiricusADM.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "emails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Email(message = "Invalid email")
    private String email;
    private Instant data_criacao;
    private Instant data_atualizacao;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usuario;


}
