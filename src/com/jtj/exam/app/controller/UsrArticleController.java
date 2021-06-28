package com.jtj.exam.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jtj.exam.Util;
import com.jtj.exam.app.Rq;
import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.dao.Article;

public class UsrArticleController {
	private List<Article> articles;
	private int lastArticleId;
	private Scanner sc;

	public UsrArticleController() {
		articles = new ArrayList<>();
		lastArticleId = 0;
		sc = Container.getSc();

		// 테스트 게시물 만들기
		makeTestData();
	}

	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			int id = lastArticleId + 1;
			String regDate = Util.getNowDateStr();
			String updateDate = Util.getNowDateStr();
			Article article = new Article(id, regDate, updateDate, title, body);

			articles.add(article);
			lastArticleId = id;
		}		
	}

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/article/write")) {
			actionWrite(rq);
		} else if (rq.getActionPath().equals("/usr/article/list")) {
			actionList(rq);
		} else if (rq.getActionPath().equals("/usr/article/detail")) {
			actionDetail(rq);
		} else if (rq.getActionPath().equals("/usr/article/delete")) {
			actionDelete(rq);
		} else if (rq.getActionPath().equals("/usr/article/modify")) {
			actionModify(rq);
		}
	}

	private void actionModify(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		System.out.println("새로운 제목: ");
		String title = sc.nextLine();

		System.out.println("새로운 내용: ");
		String body = sc.nextLine();

		foundArticle.setTitle(title);
		foundArticle.setBody(body);

		System.out.println(id + "번 게시물이 수정되었습니다.");
	}

	private void actionDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		articles.remove(foundArticle);
		System.out.println(id + "번 게시물이 삭제 되었습니다.");
	}

	private void actionDetail(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		System.out.println("번호 : " + foundArticle.getId());
		System.out.println("작성 : " + foundArticle.getRegDate());
		System.out.println("수정 : " + foundArticle.getUpdateDate());
		System.out.println("제목 : " + foundArticle.getTitle());
		System.out.println("내용 : " + foundArticle.getBody());
	}

	private void actionList(Rq rq) {
		System.out.println("번호/등록날짜/제목");
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			System.out.println(article.getId() + "/" + article.getRegDate() + "/" + article.getTitle());
		}
	}

	private void actionWrite(Rq rq) {
		System.out.println("게시물을 등록 합니다.");

		System.out.println("제목을 입력해주세요");
		String title = sc.nextLine();
		System.out.println("내용을 입력해주세요");
		String body = sc.nextLine();

		int id = lastArticleId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		Article article = new Article(id, regDate, updateDate, title, body);

		articles.add(article);
		lastArticleId = id;
		System.out.println(id + "번 게시물이 생성되었습니다.");
	}

}