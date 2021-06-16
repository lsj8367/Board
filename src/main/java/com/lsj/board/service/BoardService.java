package com.lsj.board.service;

import com.lsj.board.domain.board.Board;
import com.lsj.board.domain.board.BoardRepository;
import com.lsj.board.web.dto.BoardSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> search(){
       return boardRepository.findAll();
    }

    @Transactional
    public Long save(BoardSaveRequestDto requestDto){ //등록하기
        return boardRepository.save(requestDto.toEntity()).getId();
    }

}
