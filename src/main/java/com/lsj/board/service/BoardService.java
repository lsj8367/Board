package com.lsj.board.service;

import com.lsj.board.domain.board.ArticleLayout;
import com.lsj.board.domain.board.Board;
import com.lsj.board.domain.board.BoardRepository;
import com.lsj.board.web.dto.BoardSaveRequestDto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> search(){
       return boardRepository.findAllDesc();
    }

    public Optional<Board> searchOne(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional
    public Long save(BoardSaveRequestDto requestDto){ //등록하기
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ArticleLayout articleLayout) {
        boardRepository.findById(id)
                       .orElseThrow(() -> new IllegalArgumentException("해당하는 게시판글 없음"))
                       .setArticleLayout(articleLayout);

        return id;
    }

    @Transactional
    public void delete(Long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(){
        boardRepository.deleteAll();
    }


}
