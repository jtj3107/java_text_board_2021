package com.jtj.exam.app.container;

import java.util.Scanner;

import com.jtj.exam.app.Session;
import com.jtj.exam.app.controller.UsrArticleController;
import com.jtj.exam.app.controller.UsrMemberController;
import com.jtj.exam.app.controller.UsrSystemController;

import lombok.Getter;

public class Container {
	@Getter
	private static Scanner sc;
	@Getter
	private static Session session;
	@Getter
	private static UsrSystemController usrSystemController;
	@Getter
	private static UsrArticleController usrArticleController;
	@Getter
	private static UsrMemberController usrMemberController;

	static {
		sc = new Scanner(System.in);
		session = new Session();

		usrSystemController = new UsrSystemController();
		usrArticleController = new UsrArticleController();
		usrMemberController = new UsrMemberController();

	}
}
