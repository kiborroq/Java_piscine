package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

public class UsersServiceImplTest {
	UserRepository repository;
	UsersServiceImpl usersService;

	final User FOR_AUTHENTICATION = new User("olesya", "smart", false);
	final User AUTHENTICATED = new User("kiborroq", "kiborroq", true);

	@BeforeEach
	void setUp() {
		repository = Mockito.mock(UserRepository.class);
		usersService = new UsersServiceImpl(repository);
		FOR_AUTHENTICATION.setAuth(false);
	}

	@Test
	void user_already_authenticated() throws EntityNotFoundException {
		Mockito.when(repository.findByLogin(AUTHENTICATED.getLogin())).thenReturn(AUTHENTICATED);
		assertThrows(AlreadyAuthenticatedException.class, ()->usersService.authenticate(AUTHENTICATED.getLogin(), AUTHENTICATED.getPassword()));
	}

	@Test
	void correct_user_authentication_verify_update_method() throws EntityNotFoundException, AlreadyAuthenticatedException {
		Mockito.when(repository.findByLogin(anyString())).thenReturn(FOR_AUTHENTICATION);
		usersService.authenticate(FOR_AUTHENTICATION.getLogin(), FOR_AUTHENTICATION.getPassword());
		Mockito.verify(repository).update(FOR_AUTHENTICATION);
	}

	@Test
	void correct_user_authentication_check_return_true() throws EntityNotFoundException, AlreadyAuthenticatedException {
		Mockito.when(repository.findByLogin(anyString())).thenReturn(FOR_AUTHENTICATION);
		assertTrue(usersService.authenticate(FOR_AUTHENTICATION.getLogin(), FOR_AUTHENTICATION.getPassword()));
	}

	@Test
	void incorrect_password_check_return_false() throws EntityNotFoundException, AlreadyAuthenticatedException {
		Mockito.when(repository.findByLogin(anyString())).thenReturn(FOR_AUTHENTICATION);
		assertFalse(usersService.authenticate(FOR_AUTHENTICATION.getLogin(), ""));
	}
}
