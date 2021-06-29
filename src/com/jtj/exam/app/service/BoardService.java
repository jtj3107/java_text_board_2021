package com.jtj.exam.app.service;

import com.jtj.exam.app.container.Container;
import com.jtj.exam.app.dao.Board;
import com.jtj.exam.app.repository.BoardRepository;

public class BoardService {

	private BoardRepository boardRepository;

	public BoardService() {
		boardRepository = Container.getBoardRepository();
	}

	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}

	public void makeTestData() {
		make("notice", "공지");
		make("free", "자유");
	}

	private int make(String code, String name) {
		return boardRepository.make(code, name);
	}

}
