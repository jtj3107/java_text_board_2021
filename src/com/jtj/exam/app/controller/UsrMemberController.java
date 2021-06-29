package com.jtj.exam.app.controller;

import java.util.Scanner;

import com.jtj.exam.Util;
import com.jtj.exam.app.Rq;
import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.dao.Member;
import com.jtj.exam.app.service.MemberService;

public class UsrMemberController extends Controller{
	private Scanner sc;
	private MemberService memberService;

	public UsrMemberController() {
		sc = Container.getSc();
		memberService = Container.getMemberService();

		// 테스트 멤버 만들기
		memberService.makeTestData();
	}

	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/member/login")) {
			actionLogin(rq);
		} else if (rq.getActionPath().equals("/usr/member/logout")) {
			actionLogout(rq);
		}
	}
	
	private void actionLogout(Rq rq) {
		rq.logout();
	}

	private void actionLogin(Rq rq) {
		System.out.print("아이디를 입력해주세요: ");
		String loginId = sc.nextLine().trim();

		if (loginId.length() == 0) {
			System.out.println("아이디를 입력해주세요");
			return;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}
		System.out.print("비밀번호를 입력해주세요: ");
		String loginPw = sc.nextLine().trim();

		if (loginPw.length() == 0) {
			System.out.println("비밀번호를 입력해주세요");
			return;
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			System.out.println("비밀번호가 잘못되었습니다.");
			return;
		}
		
		rq.login(member);
		
		System.out.println(member.getNickName() + "님 환영합니다.");

	}
}
