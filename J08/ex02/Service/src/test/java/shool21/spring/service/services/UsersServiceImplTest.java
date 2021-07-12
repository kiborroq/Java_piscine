package shool21.spring.service.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UsersService;
import shool21.spring.service.config.TestApplicationConfig;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersServiceImplTest {
	UsersService usersService;

	@BeforeEach
	public void init() {
		usersService = new AnnotationConfigApplicationContext(TestApplicationConfig.class)
							.getBean("userServiceImpl", UsersService.class);
	}

	@Test
	public void correct_temporary_password() throws NoSuchFieldException, IllegalAccessException {
		String email = "vitya@school21-student.com";
		String pass = usersService.signUp(email);

		Field field = usersService.getClass().getDeclaredField("usersRepository");
		field.setAccessible(true);
		UsersRepository ur = (UsersRepository) field.get(usersService);
		field.setAccessible(false);

		User user = ur.findByEmail(email).get();
		assertEquals(pass, user.getPassword());
	}
}
