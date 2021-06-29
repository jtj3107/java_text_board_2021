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

		Article article = new Article(id, regDate, updateDate, boardId, memberId, title, body);
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

		if (article != null) {
			articles.remove(article);
		}
	}

	public List<Article> getArticles() {
		return articles;
	}

	public List<Article> getPageFilteredArticles(List<Article> boardArticles, int page, int pageCount) {
		List<Article> filteredArticles = new ArrayList<>();

		int fromIndex = (page - 1) * pageCount;
		int startIndex = boardArticles.size() - 1 - fromIndex;
		int endIndex = startIndex - pageCount + 1;

		if (endIndex < 0) {
			endIndex = 0;
		}

		for (int i = endIndex; i <= startIndex; i++) {
			Article article = boardArticles.get(i);
			filteredArticles.add(article);
		}

		return filteredArticles;
	}

	public List<Article> getKeywordFiltetedArticles(int boardId, String keyword) {
		if (boardId <= 0 && keyword.isEmpty()) {
			return articles;
		}
		List<Article> filteredArticles = new ArrayList<>();

		System.out.println(keyword);
		for (Article article : articles) {
			if (article.getBoardId() != boardId && boardId != 0) {
				continue;
			}
			if (keyword.isEmpty() == false) {
				if (article.getTitle().contains(keyword) == false) {
					continue;
				}

			}
			filteredArticles.add(article);
		}
		return filteredArticles;
	}

}
