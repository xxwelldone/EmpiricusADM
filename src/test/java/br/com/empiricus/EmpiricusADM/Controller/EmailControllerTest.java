package br.com.empiricus.EmpiricusADM.Controller;

import br.com.empiricus.EmpiricusADM.Service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {
    @MockitoSettings
    private EmailService emailService;
    @InjectMocks
    private EmailController emailController;

    @Test
    @DisplayName("Deve retornar código 200 se retornar todos os emails")
    void getAll() {
        //Arrange
        //Act
        //Assert

    }

    @Test
    void getByUser() {
    }

    @Test
    void post() {
    }

    @Test
    void delete() {
    }
}