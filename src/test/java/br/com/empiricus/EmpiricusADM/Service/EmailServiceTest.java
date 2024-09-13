package br.com.empiricus.EmpiricusADM.Service;

import br.com.empiricus.EmpiricusADM.Model.Email;
import br.com.empiricus.EmpiricusADM.Model.User;
import br.com.empiricus.EmpiricusADM.Repository.EmailRepository;
import br.com.empiricus.EmpiricusADM.Service.Exceptions.ItemNotFound;
import br.com.empiricus.EmpiricusADM.dto.EmailDTO;
import br.com.empiricus.EmpiricusADM.dto.UserEmailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private MailService mailSenderService;

    @InjectMocks
    private EmailService emailService;

    private Email existingEmail;
    private User existingUser;

    @BeforeEach
    public void setUp() {
        existingUser = new User(1L, "Sasuke Uchiha", "123456789", "password",
                Instant.now(), Instant.now(), true);

        existingEmail = new Email(1L, "sasuke.uchiha@example.com", Instant.now(), Instant.now(), existingUser);
    }

    @Test
    @DisplayName("Should return UserEmailDTO when user exists")
    public void findEmailByUser_UserExists_ReturnsUserEmailDTO() {
        // Arrange
        when(emailRepository.findAll()).thenReturn(List.of(existingEmail));

        // Act
        UserEmailDTO result = emailService.findEmailByUser(existingUser.getId());

        // Assert
        assertEquals(existingUser.getId(), result.getUser().getId());
    }

    @Test
    @DisplayName("Should save a new email and return EmailDTO")
    public void createEmail_ValidEmail_CallsRepoSave() {
        // Arrange
        Email newEmail = new Email(null, "neji.hyuga@example.com", Instant.now(), Instant.now(), existingUser);
        when(emailRepository.save(any(Email.class))).thenReturn(newEmail);

        // Act
        EmailDTO result = emailService.createEmail(newEmail);

        // Assert
        assertEquals(newEmail.getEmail(), result.getEmail());
        verify(emailRepository, times(1)).save(newEmail);
    }

    @Test
    @DisplayName("Should delete an existing email successfully")
    public void deleteEmail_EmailExists_DeletesEmail() {
        // Arrange
        when(emailRepository.findById(1L)).thenReturn(Optional.of(existingEmail));

        // Act & Assert
        assertDoesNotThrow(() -> emailService.deleteEmail(1L));
        verify(emailRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw ItemNotFound exception when email does not exist")
    public void deleteEmail_EmailDoesNotExist_ThrowsItemNotFound() {
        // Arrange
        when(emailRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ItemNotFound.class, () -> emailService.deleteEmail(999L));
        verify(emailRepository, never()).deleteById(999L);
    }
}
