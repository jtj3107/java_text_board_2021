package com.jtj.exam.app.service;

import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.dao.Member;
import com.jtj.exam.app.repository.MemberRepository;

public class MemberService {

	private MemberRepository memberRepository;
	
	public MemberService() {
		memberRepository = Container.getMemberRepository();
	}

	public void makeTestData() {
		for (int i = 0; i < 2; i++) {
			String loginId = "user" + (i + 1);
			String loginPw = loginId;
			String name = "홍길동" + (i + 1);
			String nickName = "강바람" + (i + 1);

			join(loginId, loginPw, name, nickName);
		}
	}

	private int join(String loginId, String loginPw, String name, String nickName) {
		return memberRepository.join(loginId, loginPw, name, nickName);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
