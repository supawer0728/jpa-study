package com.example.jpastudy;

import static java.util.stream.Collectors.toList;

import com.example.jpastudy.board.domain.Board;
import com.example.jpastudy.board.domain.Board.Param;
import com.example.jpastudy.board.domain.BoardRepository;
import com.example.jpastudy.member.domain.Member;
import com.example.jpastudy.member.domain.MemberRepository;
import com.example.jpastudy.order.domain.Order;
import com.example.jpastudy.order.domain.OrderLine;
import com.example.jpastudy.order.domain.OrderRepository;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class JpaStudyApplication implements ApplicationRunner {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaStudyApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                                                     .stringLengthRange(3, 5)
                                                     .collectionSizeRange(2, 3)
                                                     .randomize(Integer.class, (Supplier<Integer>) () -> Math.abs(EnhancedRandom.random(Integer.class) % 5))
                                                     .build();

        int memberSize = 5;
        List<Member> members = random.objects(Member.class, memberSize).collect(toList());
        memberRepository.saveAll(members);

        List<Board> initBoards = IntStream.range(0, 1)
                                          .mapToObj(number -> Board.create(new Param("title" + number, "content" + number, members.get(number % memberSize).getId())))
                                          .collect(toList());
        boardRepository.saveAll(initBoards);

        List<Order> orders = IntStream.range(0, 101)
                                      .mapToObj(number -> Order.create(random.objects(OrderLine.class, 3).collect(toList()), members.get(number % memberSize)))
                                      .collect(toList());
        orderRepository.saveAll(orders);

        log.info("ALL DATA INITIALIZED");
    }
}
