package com.example.jpastudy.board.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByTitleContains(String title);

    List<Board> findAllByWriterStartsWith(String writer);

    List<Board> findAllByTitleAndWriter(String title, String writer);
}
