package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class ChatRoom {
	private Long id;
	private String name;
	private User owner;
	private List<Message> messages;

	public ChatRoom(Long id, String name, User owner, List<Message> messages) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.messages = messages;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public User getOwner() {
		return owner;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChatRoom chatRoom = (ChatRoom) o;
		return id == chatRoom.id && name.equals(chatRoom.name) && owner.equals(chatRoom.owner) && messages.equals(chatRoom.messages);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, owner, messages);
	}

	@Override
	public String toString() {
		return "ChatRoom{" +
				"id=" + id +
				", name='" + name + '\'' +
				", owner=" + owner +
				", messages=" + messages +
				'}';
	}
}
