package com.lsj.board.web;

import com.lsj.board.domain.board.Board;
import com.lsj.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BoardService boardService;

    @RequestMapping("/")
    public String index(Model model){
        List<Board> boardList = boardService.search();

        model.addAttribute("boardList", boardList);
        return "index";
    }

    @RequestMapping("/saveBoard")
    public String boardSave(){
        return "saveBoard";
    }

    @RequestMapping("/updateBoard/{id}")
    public String updateBoard(@PathVariable Long id, Model model){
        Optional<Board> board = boardService.searchOne(id);
        Board result = board.orElseThrow(() -> new NoSuchElementException("게시글 없음"));

        model.addAttribute("board", result);

        return "updateBoard";
    }
}
