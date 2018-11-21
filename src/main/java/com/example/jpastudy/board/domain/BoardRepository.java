package com.example.jpastudy.board.domain;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByTitleContains(String title, Pageable pageable);

    List<Board> findAllByWriterStartsWith(String writer);

    List<Board> findAllByTitleAndWriter(String title, String writer);
}
