package com.example.jpastudy;

import com.example.jpastudy.board.domain.Board;
import com.example.jpastudy.board.domain.Board.Param;
import com.example.jpastudy.board.domain.BoardRepository;
import com.example.jpastudy.member.domain.Member;
import com.example.jpastudy.member.domain.MemberRepository;
import io.github.benas.randombeans.api.EnhancedRandom;
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
    private final MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaStudyApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int memberSize = 5;
        List<Member> members = EnhancedRandom.randomListOf(memberSize, Member.class);
        memberRepository.saveAll(members);

        List<Board> initBoards = IntStream.range(0, 20)
                                          .mapToObj(number -> Board.create(new Param("title" + number, "content" + number, members.get(number % memberSize).getId())))
                                          .collect(Collectors.toList());
        boardRepository.saveAll(initBoards);
    }
}
