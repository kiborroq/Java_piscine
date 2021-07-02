package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UsersRepositoryJdbcImpl implements UsersRepository {

	Connection con;

	public UsersRepositoryJdbcImpl(DataSource ds) throws SQLException {
		con = ds.getConnection();
	}

	@Override
	public List<User> findAll(int page, int size) {
		try {
			PreparedStatement st = con.prepareStatement(getFindAllQuery(page, size));
			ResultSet rs = st.executeQuery();

			List<User> users = new LinkedList<>();
			Map<Long, ChatRoom> rooms = new HashMap<>();

			if (rs.next())
				while (putNextUser(rs, users, rooms)) {}

			return users;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	private boolean putNextUser(ResultSet rs, List<User> users, Map<Long, ChatRoom> rooms) throws SQLException {
		User u = new User(
			rs.getLong("id_u"),
			rs.getString("login_u"),
			rs.getString("password_u"),
			new LinkedList<>(),
			new LinkedList<>()
		);
		users.add(u);

		while (u.getId() == rs.getLong("id_u")) {
			Long id_r = rs.getLong("id_r");

			ChatRoom tmp = rooms.get(id_r);
			if (tmp == null) {
				tmp = new ChatRoom(id_r, rs.getString("name_r"), null, null);
				rooms.put(tmp.getId(), tmp);
			}

			u.getSocializedRooms().add(tmp);
			if (u.getId() == rs.getLong("owner_id_r"))
				u.getCreatedRooms().add(tmp);
			if (!rs.next())
				return false;
		}
		return true;
	}

	private String getFindAllQuery(int page, int size) {
		return " WITH u AS (SELECT * FROM users WHERE id >" + (page - 1) * size + " LIMIT " + size + ") " +
				"SELECT * FROM (SELECT id AS id_u, login AS login_u, password AS password_u FROM u) AS tmp_u LEFT JOIN " +
				"user_room ON tmp_u.id_u=user_room.user_id LEFT JOIN " +
				"(SELECT id AS id_r, name AS name_r, owner_id AS owner_id_r FROM rooms) AS tmp_r ON room_id=tmp_r.id_r;";
	}
}
