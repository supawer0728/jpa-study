package com.example.jpastudy.board.application;

import com.example.jpastudy.board.domain.Board;
import com.example.jpastudy.board.domain.Board.Param;
import com.example.jpastudy.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board create(Board.Param param) {
        Board board = Board.create(param);
        return boardRepository.save(board);
    }

    @Transactional
    public Board modify(Param param) {
        Board board = boardRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no board: " + param.getId()));
        board.modify(param);
        return board;
    }
}
