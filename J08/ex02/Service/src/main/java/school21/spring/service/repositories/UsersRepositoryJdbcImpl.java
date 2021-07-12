package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryJdbcImpl")
public class UsersRepositoryJdbcImpl implements UsersRepository {
	DataSource ds;

	@Autowired
	public UsersRepositoryJdbcImpl(@Qualifier("hikariDS") DataSource ds) {
		this.ds = ds;
	}

	@Override
	public User findByID(Long id) {
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(String.format("select * from users where id=%d;", id));
			ResultSet rs = ps.executeQuery();
			if (!rs.next())
				return null;
			return initUser(rs);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new LinkedList<>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(String.format("select * from users;"));
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				users.add(initUser(rs));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return users;
	}

	@Override
	public void save(User entity) {
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(String.format("insert into users(email, password) values('%s', '%s');", entity.getEmail(), entity.getPassword()));
			ps.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void update(User entity) {
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(String.format("update users set email='%s' password='%s' where id=%d;", entity.getEmail(), entity.getPassword(), entity.getId()));
			ps.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(String.format("delete from users where id=%d;", id));
			ps.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public Optional<User> findByEmail(String email) {
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(String.format("select * from users where email='%s';", email));
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return Optional.of(initUser(rs));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return Optional.empty();
	}

	private User initUser(ResultSet rs) throws SQLException {
		return new User(
				rs.getLong("id"),
				rs.getString("email"),
				rs.getString("password")
		);
	}
}
