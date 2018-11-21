package com.example.jpastudy.board.domain;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Entity
@Table(name = "board")
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue
    private Long id;

    private String title;
    private String content;

    private String writer;
    @Column(name = "register_datetime")
    private ZonedDateTime wroteAt;
    @Column(name = "modified_datetime")
    private ZonedDateTime modifiedAt;

    // JPA 스펙
    protected Board() {
    }

    public static Board create(String title, String content, String writer) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);

        ZonedDateTime now = ZonedDateTime.now();
        board.wroteAt = now;
        board.modifiedAt = now;

        return board;
    }

    private void setTitle(@NonNull String title) {
        this.title = title;
    }

    private void setContent(@NonNull String content) {
        this.content = content;
    }

    private void setWriter(@NonNull String writer) {
        this.writer = writer;
    }
}
