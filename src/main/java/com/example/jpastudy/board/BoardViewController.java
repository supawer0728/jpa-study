package com.example.jpastudy.board;

import static org.springframework.data.domain.Sort.Direction.DESC;

import com.example.jpastudy.board.application.BoardService;
import com.example.jpastudy.board.domain.Board;
import com.example.jpastudy.board.domain.BoardRepository;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/view/board")
public class BoardViewController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(@PageableDefault(size = 5, sort = "id", direction = DESC) Pageable pageable, Map<String, Object> model) {
        model.put("boards", boardRepository.findAll(pageable));
        return "/board/list";
    }

    @GetMapping(path = "/list", params = "title")
    public String list(@RequestParam String title, @PageableDefault(size = 5, sort = "id", direction = DESC) Pageable pageable, Map<String, Object> model) {
        model.put("boards", boardRepository.findAllByTitleContains(title, pageable));
        return "/board/list";
    }

    @ResponseBody
    @GetMapping(path = "/list.json")
    public Page<Board> list(@RequestParam(required = false) String title, @PageableDefault(size = 5, sort = "id", direction = DESC) Pageable pageable) {
        return Optional.ofNullable(title)
                       .map(t -> boardRepository.findAllByTitleContains(t, pageable))
                       .orElseGet(() -> boardRepository.findAll(pageable));
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
