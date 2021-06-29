package com.jtj.exam.app.controller;

import java.util.List;
import java.util.Scanner;

import com.jtj.exam.app.Rq;
import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.dao.Article;
import com.jtj.exam.app.dao.Board;
import com.jtj.exam.app.dao.Member;
import com.jtj.exam.app.service.ArticleService;
import com.jtj.exam.app.service.BoardService;
import com.jtj.exam.app.service.MemberService;

public class UsrArticleController extends Controller {
	private ArticleService articleService;
	private Scanner sc;
	private BoardService boardService;
	private MemberService memberService;

	public UsrArticleController() {
		articleService = Container.getArticleService();
		sc = Container.getSc();
		boardService = Container.getBoardService();
		memberService = Container.getMemberService();

		// 테스트 게시물 만들기
		makeTestData();
	}

	private void makeTestData() {
		boardService.makeTestData();
		articleService.makeTestData();
	}

	@Override
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

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		int loginedMemberId = rq.getLogineMemberId();

		if (article.getMemberId() != loginedMemberId) {
			System.out.println("해당 게시물 작성자만 수정 가능합니다.");
			return;
		}

		System.out.println("새로운 제목: ");
		String title = sc.nextLine();

		System.out.println("새로운 내용: ");
		String body = sc.nextLine();

		article.setTitle(title);
		article.setBody(body);

		System.out.println(id + "번 게시물이 수정되었습니다.");
	}

	private void actionDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		int loginedMemberId = rq.getLogineMemberId();

		if (article.getMemberId() != loginedMemberId) {
			System.out.println("해당 게시물 작성자만 삭제 가능합니다.");
			return;
		}

		articleService.deleteArticle(article.getId());
		System.out.println(id + "번 게시물이 삭제 되었습니다.");
	}

	private void actionDetail(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}
		Board board = boardService.getBoardById(article.getBoardId());
		Member member = memberService.getMemberById(article.getMemberId());

		System.out.println(board.getName() + "게시판");
		System.out.println("번호 : " + article.getId());
		System.out.println("작성 : " + article.getRegDate());
		System.out.println("수정 : " + article.getUpdateDate());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("작성자 : " + member.getNickName());
	}

	private void actionList(Rq rq) {
		List<Article> articles = articleService.getArticles();

		System.out.println("게시판이름/번호/등록날짜/제목/작성자");
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			Board board = boardService.getBoardById(article.getBoardId());
			Member member = memberService.getMemberById(article.getMemberId());

			System.out.println(board.getName() + "/" + article.getId() + "/" + article.getRegDate() + "/"
					+ article.getTitle() + "/" + member.getNickName());
		}
	}

	private void actionWrite(Rq rq) {
		int boardId = rq.getIntParam("boardId", 0);

		if (boardId == 0) {
			System.out.println("boardId를 입력해주세요.");
			return;
		}

		Board board = boardService.getBoardById(boardId);

		if (board == null) {
			System.out.println("존재하지 않는 게시판 번호입니다.");
			return;
		}

		System.out.println("== " + board.getName() + "게시판 글작성 ==");

		System.out.println("제목을 입력해주세요");
		String title = sc.nextLine();
		System.out.println("내용을 입력해주세요");
		String body = sc.nextLine();

		int loginedMemberId = rq.getLogineMemberId();

		int id = articleService.write(board.getId(), loginedMemberId, title, body);

		System.out.println(id + "번 게시물이 생성되었습니다.");
	}

}