package com.example.jpastudy;

import com.example.jpastudy.board.domain.Board;
import com.example.jpastudy.board.domain.Board.Param;
import com.example.jpastudy.board.domain.BoardRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class JpaStudyApplication implements ApplicationRunner {

    private final BoardRepository boardRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaStudyApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Board> initBoards = IntStream.range(0, 51)
                                          .mapToObj(number -> Board.create(new Param("title" + number, "content" + number, "writer" + number)))
                                          .collect(Collectors.toList());
        boardRepository.saveAll(initBoards);
    }
}
