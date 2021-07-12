package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UserRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        UserRepository userRepository = context.getBean("usersRepositoryJdbc", UserRepository.class);
        test(userRepository);
        userRepository = context.getBean("usersRepositoryJdbcTemplate", UserRepository.class);
        test(userRepository);
    }

    public static void test(UserRepository userRepository) {
        System.out.println("findById(1L):\n" + userRepository.findByID(1L));
        System.out.println("findAll:\n" + userRepository.findAll());
        userRepository.save(new User("gagustin@school21-student.com"));
        System.out.println("findAll after save(gagustin@school21-student.com):\n" + userRepository.findAll());
        userRepository.update(new User(userRepository.findByEmail("gagustin@school21-student.com").get().getId(),
                "rbliss@school21-student.com"));
        System.out.println("findAll after update(gagustin@school21-student.com -> rbliss@school21-student.com):\n" + userRepository.findAll());
        userRepository.delete(userRepository.findByEmail("rbliss@school21-student.com").get().getId());
        System.out.println("findAll after delete(2L):\n" + userRepository.findAll());
        System.out.println();
    }
}
