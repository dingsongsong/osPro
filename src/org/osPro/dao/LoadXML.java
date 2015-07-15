package org.osPro.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.struts2.ServletActionContext;
import org.osPro.model.User;
import org.osPro.model.UserHandler;
import org.xml.sax.SAXException;

import com.opensymphony.xwork2.ActionContext;

public class LoadXML {
	public List<User> load(){
		SAXParserFactory saxF = SAXParserFactory.newInstance();
		try {
			SAXParser parser = saxF.newSAXParser();
			UserHandler handler = new UserHandler();
			ActionContext ac = ActionContext.getContext();
			ServletContext sc = (ServletContext) ac
					.get(ServletActionContext.SERVLET_CONTEXT);
			String temp = ServletActionContext.getServletContext().getRealPath(
					"/");
			// File f = new
			// File("E:/tomcat7/webapps/osPro/WEB-INF/classes/org/osPro/model/mapping/user.xml");
			parser.parse(temp
					+ "/WEB-INF/classes/org/osPro/model/mapping/user.xml",
					handler);
			return handler.getList();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
