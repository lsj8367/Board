package com.lsj.board.web;

import com.lsj.board.domain.board.ArticleLayout;
import com.lsj.board.domain.board.Board;
import com.lsj.board.service.BoardService;
import com.lsj.board.web.dto.BoardSaveRequestDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/v1/board")
    public List<Board> search(){
        return boardService.search();
    }

    @PostMapping("/api/v1/board/{id}")
    public Optional<Board> searchOne(@PathVariable Long id){
        return boardService.searchOne(id);
    }

    @PostMapping("/api/v1/board/save")
    public Long save(@RequestBody BoardSaveRequestDto requestDto){
        return boardService.save(requestDto);
    }

    @PutMapping("/api/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleLayout articleLayout){
        return boardService.update(id, articleLayout);
    }

    @DeleteMapping("/api/v1/board/{id}")
    public void delete(@PathVariable Long id){

        boardService.delete(id);
    }
}
