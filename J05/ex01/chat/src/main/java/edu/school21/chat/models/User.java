package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
	private Long id;
	private String login;
	private String password;
	private List<ChatRoom> createdRooms;
	private List<ChatRoom> socializedRooms;

	public User(Long id, String login, String password, List<ChatRoom> createdRooms, List<ChatRoom> socializedRooms) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.createdRooms = createdRooms;
		this.socializedRooms = socializedRooms;
	}

	public User() {

	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public List<ChatRoom> getCreatedRooms() {
		return createdRooms;
	}

	public List<ChatRoom> getSocializedRooms() {
		return socializedRooms;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCreatedRooms(List<ChatRoom> createdRooms) {
		this.createdRooms = createdRooms;
	}

	public void setSocializedRooms(List<ChatRoom> socializedRooms) {
		this.socializedRooms = socializedRooms;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id && login.equals(user.login) && password.equals(user.password) && createdRooms.equals(user.createdRooms) && socializedRooms.equals(user.socializedRooms);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, password, createdRooms, socializedRooms);
	}

	@Override
	public String toString() {
		return "User{" +
				"userID=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", createdRooms=" + createdRooms +
				", socializeRooms=" + socializedRooms +
				'}';
	}
}
