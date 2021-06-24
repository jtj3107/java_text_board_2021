package com.jtj.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jtj.exam.Util;
import com.jtj.exam.app.dao.Article;

public class App {
	public static void run() {
		System.out.println("== 텍스트 게시판 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		
		int lastArticleId = 0;
		
		for(int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			int id = lastArticleId +1;
			String regDate = Util.getNowDateStr();
			String updateDate = Util.getNowDateStr();
			Article article = new Article(id, regDate, updateDate, title, body);
			
			articles.add(article);
			lastArticleId = id;
		}
		while(true) {
			System.out.print("명령어) ");
			
			String command = sc.nextLine();
			
			if(command.equals("/usr/system/exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			else if(command.equals("/usr/article/write")) {
				System.out.println("게시물을 등록 합니다.");
				
				System.out.println("제목을 입력해주세요");
				String title = sc.nextLine();
				System.out.println("내용을 입력해주세요");
				String body = sc.nextLine();
			
				int id = lastArticleId +1;
				String regDate = Util.getNowDateStr();
				String updateDate = Util.getNowDateStr();
				Article article = new Article(id, regDate, updateDate, title, body);
				
				articles.add(article);
				lastArticleId = id;
				System.out.println(id + "번 게시물이 생성되었습니다.");
			}
			else if(command.equals("/usr/article/list")) {
				System.out.println("번호/등록날짜/제목");
				for(int i = articles.size() -1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.println(article.getId() + "/" + article.getRegDate() + "/" + article.getTitle());
				}
			}
		}
		
		System.out.println("== 텍스트 게시판 끝 ==");
	}
	
}
