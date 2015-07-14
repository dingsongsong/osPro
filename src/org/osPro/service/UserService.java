package org.osPro.service;

import java.util.List;

import org.osPro.dao.Item;
import org.osPro.dao.UserDao;
import org.osPro.model.User;

public class UserService {
	private UserDao dao = new UserDao();
	public List<Item> readerOrWriter(List<User> list){
		return dao.readerOrWriter(list);
	}
}
