package br.com.empiricus.EmpiricusADM.Service;

import br.com.empiricus.EmpiricusADM.Model.User;
import br.com.empiricus.EmpiricusADM.Repository.UserRepository;
import br.com.empiricus.EmpiricusADM.Model.Exceptions.ItemNotFound;
import br.com.empiricus.EmpiricusADM.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User existingUser;
    private User newUser;

    @BeforeEach
    public void setUp() {
        existingUser = new User(1L, "Sasuke Uchiha", "123456789", "password",
                Instant.now(), Instant.now(), true);

        newUser = new User(null, "Neji Hyuga", "987654321", "newpassword",
                Instant.now(), Instant.now(), false);
    }

    @Test
    @DisplayName("Should return UserDTO when the user exists")
    public void testFindUserById_UserExists_ReturnsUserDTO() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        // Act
        UserDTO result = userService.findUserById(1L);

        // Assert
        assertEquals(existingUser.getId(), result.getId());
        assertEquals(existingUser.getNome(), result.getNome());
    }

    @Test
    @DisplayName("Should throw ItemNotFound when the user does not exist")
    public void testFindUserById_UserDoesNotExist_ThrowsItemNotFound() {
        // Arrange
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ItemNotFound.class, () -> userService.findUserById(999L));
    }

    @Test
    @DisplayName("Should return a list of UserDTOs when there are users")
    public void testFindAllUsers_UsersExist_ReturnsListOfUserDTOs() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(existingUser);
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<UserDTO> result = userService.findAllUsers();

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(existingUser.getId(), result.get(0).getId());
    }

    @Test
    @DisplayName("Should return an empty list when there are no users")
    public void testFindAllUsers_NoUsersExist_ReturnsEmptyList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<UserDTO> result = userService.findAllUsers();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should create a user and return UserDTO")
    public void testCreateUser_ValidUser_ReturnsUserDTO() {
        // Arrange
        User savedUser = new User(2L, "Neji Hyuga", "987654321", "encodedpassword",
                Instant.now(), Instant.now(), false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserDTO result = userService.createUser(newUser);

        // Assert
        assertEquals(savedUser.getId(), result.getId());
        assertEquals(savedUser.getNome(), result.getNome());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    @DisplayName("Should throw exception when an error occurs during user creation")
    public void testCreateUser_ExceptionOccurs_ThrowsException() {
        // Arrange
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Error saving user"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.createUser(newUser));
    }

    @Test
    @DisplayName("Should delete the user when the user exists")
    public void testDeleteUser_UserExists_DeletesUser() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        doNothing().when(userRepository).deleteById(1L);

        // Act
        assertDoesNotThrow(() -> userService.deleteUser(1L));

        // Assert
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw ItemNotFound when the user does not exist during deletion")
    public void testDeleteUser_UserDoesNotExist_ThrowsItemNotFound() {
        // Arrange
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        assertThrows(ItemNotFound.class, () -> userService.deleteUser(999L));
        // Assert
        verify(userRepository, never()).deleteById(999L);
    }
}
