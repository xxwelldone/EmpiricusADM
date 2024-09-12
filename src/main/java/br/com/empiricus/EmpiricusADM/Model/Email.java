package br.com.empiricus.EmpiricusADM.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "emails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Instant data_criacao;
    private Instant data_atualizacao;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usuario;


}
