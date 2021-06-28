package com.jtj.exam.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

import com.jtj.exam.Util;
import com.jtj.exam.app.Rq;
import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.dao.Member;

public class UsrMemberController {
	private List<Member> members;
	private int lastMemberId;
	private Scanner sc;

	public UsrMemberController() {
		members = new ArrayList<>();
		lastMemberId = 0;
		sc = Container.getSc();

		// 테스트 멤버 만들기
		makeTestData();
	}

	private void makeTestData() {
		for (int i = 0; i < 2; i++) {
			int id = lastMemberId + 1;
			String regDate = Util.getNowDateStr();
			String updateDate = Util.getNowDateStr();
			String loginId = "user" + (i + 1);
			String loginPw = loginId;
			String name = "홍길동" + (i + 1);
			String nickName = "강바람" + (i + 1);

			Member member = new Member(id, regDate, updateDate, loginId, loginPw, name, nickName);

			members.add(member);
			lastMemberId = id;
		}
	}

	private Member getMemberByLoginId(String loginId) {
		for (Member member :members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/member/login")) {
			actionLogin(rq);
		} else if (rq.getActionPath().equals("/usr/member/logout")) {
			actionlogout(rq);
		}
	}

	private void actionlogout(Rq rq) {
		rq.removeSessionAttr("loginedMember");
	}

	private void actionLogin(Rq rq) {
		System.out.print("아이디를 입력해주세요: ");
		String loginId = sc.nextLine().trim();

		if (loginId.length() == 0) {
			System.out.println("아이디를 입력해주세요");
			return;
		}

		Member member = getMemberByLoginId(loginId);

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
		
		rq.setSessionAttr("loginedMember", member);
		
		System.out.println(member.getNickName() + "님 환영합니다.");

	}
}
