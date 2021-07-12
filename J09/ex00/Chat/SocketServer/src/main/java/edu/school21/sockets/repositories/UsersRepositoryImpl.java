package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UsersRepositoryImpl implements UsersRepository {
	DataSource ds;
	JdbcTemplate jt;

	RowMapper<User> rm = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int i) throws SQLException {
			return new User(
					rs.getLong("id"),
					rs.getString("login"),
					rs.getString("password")
				);
		}
	};

	@Autowired
	public UsersRepositoryImpl(DataSource ds) {
		this.ds = ds;
		this.jt = new JdbcTemplate(ds);
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
		this.jt = new JdbcTemplate(ds);
	}

	@Override
	public List<User> findAll() {
		return jt.query("SELECT * FROM users", rm);
	}

	@Override
	public User findById(Long id) {
		return jt.queryForObject("SELECT * FROM users WHERE id=?", rm, id);
	}

	@Override
	public void save(User entity) {
		jt.update("INSERT INTO users(login, password) VALUES (?, ?)",
				entity.getLogin(),
				entity.getPassword());
	}

	@Override
	public void update(User entity) {
		jt.update("UPDATE users SET login='?' password='?' WHERE id=?",
				entity.getLogin(),
				entity.getPassword(),
				entity.getId());
	}

	@Override
	public void delete(Long id) {
		jt.update("DELETE FROM users WHERE id=?", id);
	}

	@Override
	public User findByLogin(String login) {
		return jt.queryForObject("SELECT * FROM users WHERE login='?'", rm, login);
	}
}
