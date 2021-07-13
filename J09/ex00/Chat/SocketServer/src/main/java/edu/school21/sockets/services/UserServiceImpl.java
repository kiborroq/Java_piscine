package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserServiceImpl implements UsersService {
	UsersRepository usersRepository;
	PasswordEncoder passwordEncoder;
	Map<String, User> signedInUsers;

	@Autowired
	public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
		this.signedInUsers = new HashMap<>();
	}

	@Override
	public boolean signUp(String login, String password) {
		if (password.length() == 0)
			return false;
		String pass = passwordEncoder.encode(password);
		usersRepository.save(new User(login, pass));
		return true;
	}

	@Override
	public boolean signIn(String login, String password) {
		User u = usersRepository.findByLogin(login);
		if (u == null)
			return false;
		signedInUsers.put(login, u);
		return passwordEncoder.matches(password, u.getPassword());
	}

	public UsersRepository getUsersRepository() {
		return usersRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
}
