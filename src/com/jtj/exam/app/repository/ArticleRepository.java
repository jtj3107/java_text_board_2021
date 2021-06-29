package com.jtj.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.jtj.exam.Util;
import com.jtj.exam.app.dao.Article;

public class ArticleRepository {
	private int lastId;
	private List<Article> articles;

	public ArticleRepository() {
		lastId = 0;
		articles = new ArrayList<>();
	}

	public int write(int boardId, int memberId, String title, String body) {
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		Article article = new Article(id, regDate, updateDate, boardId, memberId,title, body);
		articles.add(article);

		lastId = id;

		return id;
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void DeleteArticle(int id) {
		Article article = getArticleById(id);
		
		if(article != null) {
			articles.remove(article);
		}
	}

	public List<Article> getArticles() {
		return articles;
	}

}
