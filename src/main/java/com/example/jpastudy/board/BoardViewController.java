package com.example.jpastudy.board;

import com.example.jpastudy.board.application.BoardService;
import com.example.jpastudy.board.domain.Board;
import com.example.jpastudy.board.domain.BoardRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/view/board")
public class BoardViewController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Map<String, Object> model) {
        model.put("boards", boardRepository.findAll());
        return "/board/list";
    }

    @GetMapping(path = "/list", params = "title")
    public String list(@RequestParam String title, Map<String, Object> model) {
        model.put("boards", boardRepository.findAllByTitleContains(title));
        return "/board/list";
    }

    @GetMapping("/form")
    public String form() {
        return "/board/form";
    }

    @PostMapping("/form")
    public String create(Board.Param param) {
        boardService.create(param);
        return "redirect:/view/board/list";
    }

    @GetMapping({"/{id}/form"})
    public String form(@PathVariable("id") Long id, Map<String, Object> model) {
        model.put("board", boardRepository.findById(id).orElseThrow(() -> new RuntimeException("no board: " + id)));
        return "/board/form";
    }

//    @GetMapping({"/{id}/form"})
//    public String form(@PathVariable("id") Board board, Map<String, Object> model) {
//        model.put("board", board);
//        return "/board/form";
//    }

    @PostMapping({"/{id}/form"})
    public String modify(@PathVariable("id") Long id, Board.Param param) {
        boardService.modify(param);
        return "redirect:/view/board/list";
    }
}
