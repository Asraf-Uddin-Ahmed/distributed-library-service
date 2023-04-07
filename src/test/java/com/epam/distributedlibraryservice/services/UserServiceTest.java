package com.epam.distributedlibraryservice.services;

import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.repositories.UserRepository;
import com.epam.distributedlibraryservice.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	void whenServiceSaveMethodCalled_thenRepositorySaveMethodShouldBeCalled() {
		// Create a User object
		User userToSave = new User();
		userToSave.setUsername("username");
		userToSave.setName("John Doe");

		// Call the UserService's saveUser() method, which internally calls userRepository.save()
		userService.save(userToSave);

		// Verify that the UserRepository's save() method was called once with the userToSave object
		verify(userRepository, times(1)).save(eq(userToSave));
	}

	@Test
	void whenUserSaved_thenEncodedPasswordShouldBeMatched() {
		// Create a new User object
		User user = new User();
		user.setUsername("asraf");
		user.setName("John Doe");
		user.setEmail("asraf@example.com");
		user.setPassword("password");
		user.setUserStatus("active");

		// Mock the passwordEncoder.encode() method
		Mockito.when(passwordEncoder.encode(Mockito.any(CharSequence.class))).thenReturn("encodedPassword");

		// Mock the userRepository.save() method
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

		// Call the saveUser() method on the userService
		userService.save(user);

		// Assert that the user is saved successfully
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
		// Assert that the password is encoded
		Mockito.verify(passwordEncoder, Mockito.times(1)).encode("password");
		// Assert that the encoded password is set on the user object
		assertEquals("encodedPassword", user.getPassword());
	}

	@Test
	void whenNullUserSave_thenThrowsNullPointerException() {
		// Call the saveUser() method on the userService with null user
		assertThatThrownBy(() -> userService.save(null))
				.isInstanceOf(NullPointerException.class);
	}

	@Test
	void whenFindByUsername_thenUserShouldBeFound() {
		// Create a user with a username
		User user = new User();
		user.setUsername("asraf");

		// Mock the userRepository.findByUsername() method
		Mockito.when(userRepository.findByUsername("asraf")).thenReturn(user);

		// Call the getUserByUsername() method on the userService
		User retrievedUser = userService.findByUsername("asraf");

		// Assert that the retrieved user is not null
		assertNotNull(retrievedUser);
		// Assert that the retrieved user is the same as the mocked user
		assertEquals(user, retrievedUser);
	}

	@Test
	void whenFindByNonExistingUsername_thenUserShouldBeNull() {
		// Create a user with a username
		User user = new User();
		user.setUsername("asraf");

		// Mock the userRepository.findByUsername() method
		Mockito.when(userRepository.findByUsername("asraf")).thenReturn(user);

		// Call the getUserByUsername() method on the userService
		User retrievedUser = userService.findByUsername("abc");

		// Assert that the retrieved user is not null
		assertNull(retrievedUser);
	}

	@Test
	void whenFindByEmail_thenUserShouldBeFound() {
		// Create a user with a username
		User user = new User();
		user.setEmail("asraf@test.com");

		// Mock the userRepository.findByUsername() method
		Mockito.when(userRepository.findFirstByEmail("asraf@test.com")).thenReturn(user);

		// Call the getUserByUsername() method on the userService
		User retrievedUser = userService.getFirstByEmail("asraf@test.com");

		// Assert that the retrieved user is not null
		assertNotNull(retrievedUser);
		// Assert that the retrieved user is the same as the mocked user
		assertEquals(user, retrievedUser);
	}

	@Test
	void whenFindByNonExistingEmail_thenUserShouldBeNull() {
		// Create a user with a username
		User user = new User();
		user.setEmail("asraf@test.com");

		// Mock the userRepository.findByUsername() method
		Mockito.when(userRepository.findFirstByEmail("asraf@test.com")).thenReturn(user);

		// Call the getUserByUsername() method on the userService
		User retrievedUser = userService.getFirstByEmail("abc");

		// Assert that the retrieved user is not null
		assertNull(retrievedUser);
	}

	@Test
	void whenFindById_thenUserShouldBeFound() {
		// Create a user with a username
		User user = new User();
		user.setId(1);
		user.setEmail("asraf@test.com");

		// Mock the userRepository.findByUsername() method
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

		// Call the getUserByUsername() method on the userService
		User retrievedUser = userService.getById(1);

		// Assert that the retrieved user is not null
		assertNotNull(retrievedUser);
		// Assert that the retrieved user is the same as the mocked user
		assertEquals(user, retrievedUser);
	}

	@Test
	void whenFindByNonID_thenUserShouldBeNull() {
		// Create a user with a username
		User user = new User();
		user.setId(1);
		user.setEmail("asraf@test.com");

		// Mock the userRepository.findByUsername() method
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

		// Call the getUserByUsername() method on the userService
		User retrievedUser = userService.getById(2);

		// Assert that the retrieved user is not null
		assertNull(retrievedUser);
	}
}
