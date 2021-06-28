package com.lsj.board.web;

import com.lsj.board.domain.board.Board;
import com.lsj.board.service.BoardService;
import com.lsj.board.web.dto.BoardSaveRequestDto;
import com.lsj.board.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto){
        return boardService.update(id, requestDto);
    }
}
