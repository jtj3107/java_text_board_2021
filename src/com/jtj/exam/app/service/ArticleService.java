package com.jtj.exam.app.service;

import java.util.ArrayList;
import java.util.List;

import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.dao.Article;
import com.jtj.exam.app.repository.ArticleRepository;

public class ArticleService {
	private ArticleRepository articleRepository;
	
	public ArticleService() {
		articleRepository = Container.getArticleRepository();
	}
	public int write(int boardId, int memberId, String title, String body) {
		return articleRepository.write(boardId, memberId, title, body);
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}

	public void deleteArticle(int id) {
		articleRepository.DeleteArticle(id);	
	}
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
	public void makeTestData() {
		for (int i = 1; i <= 100; i++) {
			String title = "제목" + (i +1);
			String body = "내용" + (i +1);
			write(i %2 +1, i %2 +1, title, body);		
		}
	}
}
