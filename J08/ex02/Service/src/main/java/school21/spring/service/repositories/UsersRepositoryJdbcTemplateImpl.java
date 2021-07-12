package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryJdbcTemplateImpl")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
	DataSource ds;
	JdbcTemplate jt;

	RowMapper rowMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int i) throws SQLException {
			return new User(
					rs.getLong("id"),
					rs.getString("email"),
					rs.getString("password")
			);
		}
	};

	@Autowired
	public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDS") DataSource ds) {
		this.ds = ds;
		this.jt = new JdbcTemplate(ds);
	}

	@Override
	public User findByID(Long id) {
		return (User) jt.queryForObject("select * from users where id=?", rowMapper, id);
	}

	@Override
	public List<User> findAll() {
		return jt.query("select * from users", rowMapper);
	}

	@Override
	public void save(User entity) {
		jt.update("insert into users(email, password) values(?, ?)", entity.getEmail(), entity.getPassword());
	}

	@Override
	public void update(User entity) {
		jt.update("update users set email=? password=? where id=?", entity.getEmail(), entity.getPassword(), entity.getId());
	}

	@Override
	public void delete(Long id) {
		jt.update("delete from users where id=?", id);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		User user = (User) jt.queryForObject("select * from users where email=?", rowMapper, email);
		return Optional.of(user);
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
		this.jt = new JdbcTemplate(ds);
	}
}
