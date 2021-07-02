package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		HikariConfig config = getConfig();
		DataSource ds = new HikariDataSource(config);

//		System.out.println("Enter a id");
//		System.out.println("Enter a message");
//		System.out.print("-> ");
//		Message mes = createMessage(sc.nextLine());
		try {
//			MessagesRepository mr = new MessagesRepositoryJdbcImpl(ds);
////			mrji.save(mes);
//			Message mes = mr.findById(5L).get();
//			System.out.println(mes);
//
//			mes.setText("ldsjflskdjflk");
//			mes.setDate(null);
//			mr.update(mes);
//
//			mes = mr.findById(5L).get();
//			System.out.println(mes);
			UsersRepository ur = new UsersRepositoryJdbcImpl(ds);
			List<User> users = ur.findAll(2, 2);

			for (User u : users) {
				System.out.println(u);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		System.out.println();
	}

	private static Message createMessage(String text) {
		User user = new User(
			2L, "dmitriy", "dmitriy", null, null
		);
		ChatRoom chatRoom = new ChatRoom(
			2L, "all", user, null
		);
		Message message = new Message(
			null, user, chatRoom, text, null
		);
		return message;
	}

	private static void initTables(DataSource ds) {
		try {
			Connection con = ds.getConnection();
			PreparedStatement statement = con.prepareStatement(String.valueOf(Files.readAllBytes(Paths.get("src/main/resources/schema.sql"))));
			statement.execute();
			statement = con.prepareStatement(String.valueOf(Files.readAllBytes(Paths.get("src/main/resources/data.sql"))));
			statement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static HikariConfig getConfig() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://localhost/chat");
		config.setUsername("kiborroq");
		config.setPassword("kiborroq");
		return config;
	}
}
