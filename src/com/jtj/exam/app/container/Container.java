package com.jtj.exam.app.container;

import java.util.Scanner;

import com.jtj.exam.app.Session;

public class Container {
	private static Scanner sc;
	private static Session session;
	
	static {
		sc = new Scanner(System.in);
		session = new Session();
	}
	
	public static Scanner getSc() {
		return sc;
	}

	public static Session getSession() {
		return session;
	}
}
