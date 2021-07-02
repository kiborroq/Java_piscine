package edu.school21.chat.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Message {
	private Long id;
	User author;
	ChatRoom room;
	String text;
	Timestamp date;

	public Message(Long id, User author, ChatRoom room, String text, Timestamp date) {
		this.id = id;
		this.author = author;
		this.room = room;
		this.text = text;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public void setDate(Timestamp date) {
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
		return "Message {" +
				"\n\tid=" + id +
				"\n\tauthor=" + author +
				"\n\troom=" + room +
				"\n\ttext='" + text + '\'' +
				"\n\tdate=" + date +
				"\n}";
	}
}
