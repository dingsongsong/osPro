package org.osPro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {
	private User user;
	private List list;
	private Stack elementStack = new Stack();
	private StringBuffer content;

	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList();
	}

	// 每个标签只调用一次
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		content = new StringBuffer();
		if ("user".equals(qName)) {
			user = new User();
			user.setId(Integer.valueOf(attributes.getValue("id")));
		}
		elementStack.push(qName);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("user".equals(qName)) {
			list.add(user);
		}
		elementStack.pop();
	}

	// 可以执行多次
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = content.append(ch, start, length);
		String temp = elementStack.peek().toString();
		if ("arriveTime".equals(temp)) {
			user.setArriveTime(Integer.valueOf(content.toString().trim()));
		}
		if ("seriveTime".equals(temp)) {
			user.setSeriveTime(Integer.valueOf(content.toString().trim()));
		}
		if ("type".equals(temp)) {
			user.setType(Integer.valueOf(content.toString().trim()));
		}
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
