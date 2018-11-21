package com.example.jpastudy.board.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> findAllByTitleStartsWith(String title, Pageable pageabl);
}
