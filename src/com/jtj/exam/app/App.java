package com.jtj.exam.app;

import java.util.Scanner;

import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.controller.UsrArticleController;
import com.jtj.exam.app.controller.UsrMemberController;
import com.jtj.exam.app.dao.Member;

public class App {

	Scanner sc;

	App() {
		sc = Container.getSc();
	}

	public void run() {
		System.out.println("== 텍스트 게시판 시작 ==");

		UsrArticleController usrArticleController = new UsrArticleController();
		UsrMemberController usrMemberController = new UsrMemberController();
		Session session = Container.getSession();
		
		while (true) {
			Member loginedMember = (Member)session.getAttribute("loginedMember");
			String promprName = "명령어";
			
			if( loginedMember != null) {
				promprName = loginedMember.getNickName();
			}
			
			System.out.print(promprName + ") ");

			String command = sc.nextLine();
			
			Rq rq = new Rq(command);
		
			if (rq.isValid == false) {
				System.out.printf("명령어가 올바르지 않습니다.\n");
				continue;
			}

			if (rq.getControllerTypeCode().equals("usr")) {
				if(rq.getControllerName().equals("article")) {
					usrArticleController.performAction(rq);
				} else if(rq.getControllerName().equals("member")){
					usrMemberController.performAction(rq);
				}else if (rq.getActionPath().equals("/usr/system/exit")) {
					System.out.println("프로그램을 종료 합니다.");
					break;
				} 
			}
		}
		System.out.println("== 텍스트 게시판 끝 ==");
	}
}
