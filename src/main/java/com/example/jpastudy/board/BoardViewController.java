package com.example.jpastudy.board;

import com.example.jpastudy.board.domain.BoardRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/view/board")
public class BoardViewController {

    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Map<String, Object> model) {
        model.put("boards", boardRepository.findAll());
        return "/board/list";
    }
}
