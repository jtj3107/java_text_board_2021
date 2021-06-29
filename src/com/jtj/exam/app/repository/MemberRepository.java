package com.jtj.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.jtj.exam.Util;
import com.jtj.exam.app.dao.Member;

public class MemberRepository {
		private int lastId;
		private List<Member> members;

		public MemberRepository() {
			lastId = 0;
			members = new ArrayList<>();
		}

	public int join(String loginId, String loginPw, String name, String nickName) {
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		Member member = new Member(id, regDate, updateDate, loginId, loginPw, name, nickName);
		members.add(member);

		lastId = id;

		return id;
	}

	
	public Member getMemberByLoginId(String loginId) {
		for (Member member :members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberById(int id) {
		for(Member member : members) {
			if(member.getId() == id) {
				return member;
			}
		}
		return null;
	}

}
