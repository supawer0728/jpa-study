package com.example.jpastudy.board.domain;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

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

    public void modify(Param param) {
        checkValidate(param);
        if (param.hasTitle()) {
            setTitle(param.getTitle());
        }
        if (param.hasContent()) {
            setContent(param.getContent());
        }
    }

    private void checkValidate(Param param) {
        if (!this.writer.equals(param.getWriter())) {
            throw new RuntimeException("no authority");
        }
    }

    public static Board create(@NonNull Param param) {
        param.checkValidToRegister();
        Board board = new Board();
        board.setTitle(param.getTitle());
        board.setContent(param.getContent());
        board.setWriter(param.getWriter());

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

    @Data
    @NoArgsConstructor
    public static class Param {

        private Long id;
        private String title;
        private String content;
        private String writer;

        public Param(String title, String content, String writer) {
            this.title = title;
            this.content = content;
            this.writer = writer;
        }

        void checkValidToRegister() {
            if (StringUtils.isBlank(this.title)) {
                throw new IllegalArgumentException("title is required");
            }
            if (StringUtils.isBlank(this.content)) {
                throw new IllegalArgumentException("content is required");
            }
            if (StringUtils.isBlank(this.writer)) {
                throw new IllegalArgumentException("writer is required");
            }
        }

        boolean hasTitle() {
            return StringUtils.isNotBlank(title);
        }

        boolean hasContent() {
            return StringUtils.isNotBlank(content);
        }
    }
}
