package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

	private Connection con = null;

	public MessagesRepositoryJdbcImpl(DataSource ds) throws SQLException {
		con = ds.getConnection();
	}

	@Override
	public Optional<Message> findById(Long id) {
		Message message = null;
		try {
			String query = getFindQuery(id);
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
			{
				User user = new User(
					rs.getLong("author_id"),
					rs.getString("login"),
					rs.getString("password"),
					null, null
				);
				ChatRoom chatRoom = new ChatRoom(
					rs.getLong("room_id"),
					rs.getString("name"),
					user, null
				);
				message = new Message(
					id,
					user,
					chatRoom,
					rs.getString("text"),
					rs.getTimestamp("date")
				);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return message == null ? Optional.empty() : Optional.ofNullable(message);
	}

	@Override
	public void save(Message message) {
		try {
			if (!validMessage(message))
				throw new NotSavedSubEntityException();

			String query = getSaveQuery(message);
			PreparedStatement statement = con.prepareStatement(query);
			statement.execute();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void update(Message message) {
		try {
			String query = getUpdateQuery(message);
			PreparedStatement statement = con.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private String getUpdateQuery(Message message) {
		return "UPDATE messages SET text='" + message.getText() + "', date=" + message.getDate() +
				" WHERE id=" + message.getId() + ";";
	}

	private String getSaveQuery(Message message) {
		return	"INSERT INTO messages(author_id, room_id, text, date)" +
				"VALUES (" + message.getAuthor().getId() + ", " +
				message.getRoom().getId() + ", '" +
				message.getText() + "', current_timestamp);";
	}

	private String getFindQuery(Long id) {
		return	"SELECT * FROM (SELECT * FROM messages WHERE messages.id=" + id +
				") AS message LEFT JOIN users ON message.author_id=users.id LEFT JOIN rooms ON message.room_id=rooms.id;";
	}

	private boolean validMessage(Message message) throws SQLException {
		Long user_id = message.getAuthor().getId();
		Long room_id = message.getRoom().getId();

		if (user_id == null || room_id == null)
			return false;

		if (!idExistsInTable(user_id, "users") || !idExistsInTable(room_id, "rooms"))
			return false;
		return true;
	}

	private boolean idExistsInTable(Long id, String table) throws SQLException {
		return con.prepareStatement("SELECT id FROM " + table + " WHERE id=" + id).executeQuery().next();
	}
}
