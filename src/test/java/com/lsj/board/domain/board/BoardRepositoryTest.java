package com.lsj.board.domain.board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // 테스트하고서는 항상 DB 삭제
    @AfterEach
    public void cleanDatas(){
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("")
    public void create(){
        //given
        String title   = "제목";
        String content = "test";
        String author  = "abc@test.com";

        //when
        Board board = boardRepository.save(Board.builder()
                                     .title(title)
                                     .content(content)
                                     .author(author)
                                     .build());
        //then
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getAuthor()).isEqualTo(author);
    }

    @Test
    void read(){
        boardRepository.save(Board.builder()
                                  .title("test")
                                  .content("test")
                                  .author("test")
                                  .build());

        Optional<Board> optBoard = boardRepository.findById(1L);
        optBoard.ifPresent(b -> {
            assertThat(b.getTitle()).isEqualTo("test");
            assertThat(b.getContent()).isEqualTo("test");
            assertThat(b.getAuthor()).isEqualTo("test");
        });
    }

    @Test
    void update(){
        Board board = boardRepository.save(Board.builder()
                                                .title("test")
                                                .content("test")
                                                .author("test")
                                                .build());

        board.setTitle("abc");
        board.setContent("content");
        board.setAuthor("author");

        assertThat(board.getTitle()).isEqualTo("abc");
        assertThat(board.getContent()).isEqualTo("content");
        assertThat(board.getAuthor()).isEqualTo("author");
    }

    @Test
    void delete(){
        Board board = boardRepository.save(Board.builder()
                                     .title("test")
                                     .content("test")
                                     .author("test")
                                     .build());
        Long boardId = board.getId();

        boardRepository.delete(board);

        Optional<Board> deleteBoard = boardRepository.findById(boardId);
        assertFalse(deleteBoard.isPresent());
    }
}