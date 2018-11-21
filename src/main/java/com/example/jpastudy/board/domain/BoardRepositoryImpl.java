package com.example.jpastudy.board.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    private static final QBoard board = QBoard.board;

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> findAllByTitleStartsWith(String title, Pageable pageable) {

        JPQLQuery<Board> query = from(board).where(board.title.startsWith(title));
        QueryResults<Board> results = getQuerydsl().applyPagination(pageable, query).fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
