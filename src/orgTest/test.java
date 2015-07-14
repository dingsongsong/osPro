package orgTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.osPro.model.Mutex;
import org.osPro.model.User;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		User user;
		user = new User();
		user.setId(3);
		user.setType(2);
		user.setArriveTime(6);
		user.setSeriveTime(2);
		list.add(user);
		user = new User();
		user.setId(5);
		user.setType(1);
		user.setArriveTime(6);
		user.setSeriveTime(6);
		list.add(user);
		user = new User();
		user.setId(6);
		user.setType(1);
		user.setArriveTime(6);
		user.setSeriveTime(2);
		list.add(user);
		user = new User();
		user.setId(8);
		user.setType(1);
		user.setArriveTime(10);
		user.setSeriveTime(2);
		list.add(user);

		user = new User();
		user.setId(1);
		user.setType(2);
		user.setArriveTime(9);
		user.setSeriveTime(2);
		list.add(user);
		user = new User();
		user.setId(4);
		user.setType(2);
		user.setArriveTime(9);
		user.setSeriveTime(2);
		list.add(user);
		Collections.sort(list, new Comparator<User>() {
			@Override
			public int compare(User arg0, User arg1) {
				return arg0.getArriveTime().compareTo(arg1.getArriveTime());
			}
		});
		Mutex mutex = new Mutex();
		for (int i = 0; i<100; i++) {
			for (User temp : list) {
				mutex.run(temp, i);
			}
		}
		Collections.sort(list, new Comparator<User>() {
			@Override
			public int compare(User arg0, User arg1) {
				return (arg0.getEndTime()).compareTo(arg1.getEndTime());
			}
		});
		for (int i = 0; i <= list.get(list.size()-1).getEndTime(); i++) {
			for (User temp : list) {
				temp.setCurrentTime(i);
				if (!"".equals(temp.getState())) {
					System.out.println("当前时间" + i + ":" + temp.getState()
							+ "===" + temp.getStartTime());
				}
			}
		}
	}

}
