package edu.school21.sockets.models;

import java.util.Objects;

public class User {
	private long id;
	private String login;
	private String password;

	public User(long id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id && login.equals(user.login) && password.equals(user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, password);
	}

	@Override
	public String toString() {
		return "User{" +
				"userID=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
