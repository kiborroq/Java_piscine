package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;

public class UsersServiceImpl {
	UserRepository repository;

	public UsersServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	boolean authenticate(String login, String password) throws AlreadyAuthenticatedException {
		try {
			User u = repository.findByLogin(login);
			if (u.isAuth())
				throw new AlreadyAuthenticatedException("User " + login + " has been authenticated.");

			if (u.getPassword() != password)
				return false;

			u.setAuth(true);
			repository.update(u);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
}
