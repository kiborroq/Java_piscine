package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.Random;

@Component("usersServiceImpl")
public class UsersServiceImpl implements UsersService {
	UsersRepository usersRepository;

	@Autowired
	public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplateImpl") UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public String signUp(String email) {
		String pass = generatePassword();
		usersRepository.save(new User(email, pass));
		return pass;
	}

	public UsersRepository getUsersRepository() {
		return usersRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	private String generatePassword() {
		Random rand = new Random();
		String set = new String(
				"abcdefghijklmnopqrstuvwxyz" +
						"ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
						"1234567890" +
						"!@#$%^&*()_+"
		);

		int size = rand.nextInt(10) + 10;
		char [] pass = new char[size];
		for (int i = 0; i < pass.length; i++)
			pass[i] = set.charAt(rand.nextInt(set.length() - 1));
		return String.valueOf(pass);
	}
}
