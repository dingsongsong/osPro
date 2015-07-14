package org.osPro.dao;

import java.util.ArrayList;
import java.util.List;

import org.osPro.model.User;

public class Item {
	private Integer currentTime;
	private Integer readerCount = 0;
	private Integer writerCount = 0;
	private List<User> users = new ArrayList<User>();
	private List<User> readers = new ArrayList<User>();
	private List<User> writers = new ArrayList<User>();

	public Integer getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Integer currentTime) {
		this.currentTime = currentTime;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getReaderCount() {
		return readerCount;
	}

	public void setReaderCount(Integer readerCount) {
		this.readerCount = readerCount;
	}

	public Integer getWriterCount() {
		return writerCount;
	}

	public void setWriterCount(Integer writerCount) {
		this.writerCount = writerCount;
	}

	public List<User> getReaders() {
		return readers;
	}

	public void setReaders(List<User> readers) {
		this.readers = readers;
	}

	public List<User> getWriters() {
		return writers;
	}

	public void setWriters(List<User> writers) {
		this.writers = writers;
	}

}
