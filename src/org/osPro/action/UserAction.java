package org.osPro.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osPro.dao.Item;
import org.osPro.dao.LoadXML;
import org.osPro.model.User;

import org.osPro.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private User user = new User();
	private List<Item> items = new ArrayList<Item>();
	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String add() throws Exception {
		// TODO Auto-generated method stub
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		if (session.get("users") == null) {
			List<User> users = new ArrayList<User>();
			users.add(user);
			session.put("users", users);
		} else {
			List<User> users = (List<User>) session.get("users");
			users.add(user);
			session.put("users", users);
		}
		return "add";
	}

	public String operate() throws Exception {
		// TODO Auto-generated method stub
		List<User> xmlUsers = new ArrayList<User>();
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		if (session.get("xmlUsers") != null) {
			List<User> list = (List<User>) session.get("xmlUsers");
			for (User temp : list) {
				if (temp != null) {
					temp.setStartTime(-1);
					xmlUsers.add(temp);
				}
			}
		}
		if (session.get("users") != null) {
			List<User> list = (List<User>) session.get("users");
			for (User temp : list) {
				if (temp != null) {
					temp.setStartTime(-1);
					xmlUsers.add(temp);
				}
			}
		}
		if (xmlUsers != null) {
			UserService userService = new UserService();
			items = userService.readerOrWriter(xmlUsers);
		}
		return "list";
	}

	public String loadXML() throws Exception {
		// TODO Auto-generated method stub
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		if("join".equals(method)){
			LoadXML loadXML = new LoadXML();
			session.put("xmlUsers", loadXML.load());	
		}
		else{
			session.remove("xmlUsers");
		}
		return "add";
	}
}
