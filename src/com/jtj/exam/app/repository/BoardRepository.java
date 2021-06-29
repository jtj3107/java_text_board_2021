package com.jtj.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.jtj.exam.Util;
import com.jtj.exam.app.dao.Article;
import com.jtj.exam.app.dao.Board;

public class BoardRepository {
	private int lastId;
	private List<Board> boards;

	public BoardRepository() {
		lastId = 0;
		boards = new ArrayList<>();
	}

	public Board getBoardById(int id) {
		for(Board board : boards) {
			if(board.getId() == id) {
				return board;
			}
		}
		return null;
	}

	public int make(String code, String name) {
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		Board board = new Board(id, regDate, updateDate, code, name);
		boards.add(board);

		lastId = id;

		return id;
	}

}
