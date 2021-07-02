package edu.school21.models;

public class User {
	Long id;
	String login;
	String password;
	boolean auth;

	public User(Long id, String login, String password, boolean auth) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.auth = auth;
	}

	public User(String login, String password, boolean status) {
		this.login = login;
		this.password = password;
		this.auth = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}
}
