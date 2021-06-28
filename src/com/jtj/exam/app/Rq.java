package com.jtj.exam.app;

import java.util.HashMap;
import java.util.Map;

import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.dao.Member;

public class Rq {
	private Map<String, String> params;
	private String command;
	private String controllerTypeName;
	private String controllerName;
	private String actionMethodName;
	private String queryString = "";
	public boolean isValid = true;
	
	public Rq(String command) {
		this.command = command;
		
		params = new HashMap<>();
		
		String[] commandBits = command.split("\\?", 2);
		
		if(commandBits.length == 2) {
			queryString = commandBits[1];
			
			String[] queryStringBits = queryString.split("&");
			
			for(String queryStringBit : queryStringBits) {
				String[] queryStringBitBits = queryStringBit.split("=", 2);
				String paramName = queryStringBitBits[0];
				String paramValue = queryStringBitBits[1];
				
				params.put(paramName, paramValue);
			}
		}
		
		commandBits = commandBits[0].split("/",4);
		
		if(commandBits.length != 4) {
			isValid = false;
			return;
		}
		
		controllerTypeName = commandBits[1];
		controllerName = commandBits[2];
		actionMethodName = commandBits[3];
	}
	
	public Object getActionPath() {
		return "/" + controllerTypeName + "/" + controllerName + "/" + actionMethodName;
	}
	
	public int getIntParam(String paramName, int defaultValue) {
		if(params.containsKey(paramName) == false) {
			return defaultValue;
		}
		try {	
			return Integer.parseInt(params.get(paramName));
		}
		catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public Object getControllerTypeCode() {
		return controllerTypeName;
	}

	public Object getControllerName() {
		return controllerName;
	}

	public void setSessionAttr(String key, Object value) {
		Session session = Container.getSession();
		
		session.setAttribute(key, value);
	}

	public void removeSessionAttr(String key) {
		Session session = Container.getSession();
		
		session.removeAttribute(key);
	}
}
