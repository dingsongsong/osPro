package org.osPro.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;
import org.osPro.model.Mutex;
import org.osPro.model.User;

public class UserDao {
	public List<Item> readerOrWriter(List<User> list) {
		List<Item> items = new ArrayList<Item>();
		Collections.sort(list, new Comparator<User>() {
			@Override
			public int compare(User arg0, User arg1) {
				return arg0.getArriveTime().compareTo(arg1.getArriveTime());
			}
		});
		Mutex mutex = new Mutex();
		int k = 0;
		for (int i = 0; k != list.size(); i++) {
			Item item = new Item();
			for (User temp : list) {
				mutex.run(temp, i);
				if (temp.getStartTime() != -1 && temp.getEndTime() == i) {
					k++;
				}
				temp.setCurrentTime(i);
				item.setCurrentTime(i);
				if (temp.getStartTime() == -1 && temp.getType() == 1) {
					item.getReaders().add(temp);
				}
				if (temp.getStartTime() == -1 && temp.getType() == 2) {
					item.getWriters().add(temp);
				}
				if (temp.getStartTime() != -1
						&& temp.getStartTime() <= temp.getCurrentTime()
						&& temp.getEndTime() >= temp.getCurrentTime()) {
					User user = new User();
					user.setId(temp.getId());
					user.setArriveTime(temp.getArriveTime());
					user.setCurrentTime(temp.getCurrentTime());
					user.setStartTime(temp.getStartTime());
					user.setType(temp.getType());
					user.setSeriveTime(temp.getSeriveTime());
					item.getUsers().add(user);
				}
				if (temp.getStartTime() != -1
						&& temp.getStartTime() <= temp.getCurrentTime()
						&& temp.getEndTime() > temp.getCurrentTime()
						&& temp.getType() == 1) {
					item.setReaderCount(item.getReaderCount() + 1);
				}
				if (temp.getStartTime() != -1
						&& temp.getStartTime() <= temp.getCurrentTime()
						&& temp.getEndTime() > temp.getCurrentTime()
						&& temp.getType() == 2) {
					item.setWriterCount(item.getWriterCount() + 1);
				}
			}
			items.add(item);
		}
		Collections.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item arg0, Item arg1) {
				return (arg0.getCurrentTime()).compareTo(arg1.getCurrentTime());
			}
		});
		return items;
	}
}
