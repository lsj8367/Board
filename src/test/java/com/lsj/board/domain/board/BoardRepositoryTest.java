package com.lsj.board.domain.board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // 테스트하고서는 항상 DB 삭제
    @AfterEach
    public void cleanDatas(){
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("BoardRepository Insert")
    public void insertData(){
        //given
        String title   = "제목";
        String content = "test";
        String author  = "abc@test.com";

        //when
        boardRepository.save(Board.builder()
                                  .title(title)
                                  .content(content)
                                  .author(author)
                                  .build());
        //then
        List<Board> boardList = boardRepository.findAll();

        Board board = boardList.get(0);

        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getAuthor()).isEqualTo(author);
    }

}