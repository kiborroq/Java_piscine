package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
	private long id;
	User author;
	ChatRoom room;
	String text;
	Date date;

	public Message(long id, User author, ChatRoom room, String text, Date date) {
		this.id = id;
		this.author = author;
		this.room = room;
		this.text = text;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public ChatRoom getRoom() {
		return room;
	}

	public void setRoom(ChatRoom room) {
		this.room = room;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Message message = (Message) o;
		return id == message.id && author.equals(message.author) && room.equals(message.room) && text.equals(message.text) && date.equals(message.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, author, room, text, date);
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", author=" + author +
				", room=" + room +
				", text='" + text + '\'' +
				", date=" + date +
				'}';
	}
}
