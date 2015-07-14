package org.osPro.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.struts2.ServletActionContext;
import org.osPro.dao.Item;
import org.osPro.model.User;
import org.osPro.model.UserHandler;
import org.osPro.service.UserService;
import org.xml.sax.SAXException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private User user = new User();
	private List<Item> items = new ArrayList<Item>();

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
		List<User> users = new ArrayList<User>();
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		SAXParserFactory saxF = SAXParserFactory.newInstance();
		try {
			SAXParser parser = saxF.newSAXParser();
			UserHandler handler = new UserHandler();
			ActionContext ac = ActionContext.getContext();
			ServletContext sc = (ServletContext) ac
					.get(ServletActionContext.SERVLET_CONTEXT);
			String temp = ServletActionContext.getServletContext().getRealPath("/");
			// File f = new
			// File("E:/tomcat7/webapps/osPro/WEB-INF/classes/org/osPro/model/mapping/user.xml");
			parser.parse(temp
					+ "/WEB-INF/classes/org/osPro/model/mapping/user.xml",
					handler);
			users = handler.getList();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (session.get("users") != null) {
			users.addAll((List<User>) session.get("users"));
			session.remove("users");
		}
		UserService userService = new UserService();
		items = userService.readerOrWriter(users);
		return "list";
	}
}
