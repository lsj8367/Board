package com.lsj.board.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.lsj.board.config.QuerydslConfiguration;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(QuerydslConfiguration.class)
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

        ArticleLayout articleLayout = new ArticleLayout(title, content);
        String author  = "abc@test.com";

        //when
        Board board = boardRepository.save(Board.builder()
                                     .articleLayout(articleLayout)
                                     .author(author)
                                     .build());
        //then
        assertThat(articleLayout).isEqualTo(new ArticleLayout(title, content));
        assertThat(board.getAuthor()).isEqualTo(author);
    }

    @Test
    void read(){
        String title   = "제목";
        String content = "test";

        ArticleLayout articleLayout = new ArticleLayout(title, content);

        boardRepository.save(Board.builder()
                       .articleLayout(articleLayout)
                       .author("test")
                       .build());

        Optional<Board> optBoard = boardRepository.findById(1L);
        optBoard.ifPresent(b -> {
            assertThat(b.getArticleLayout().getTitle()).isEqualTo(title);
            assertThat(b.getArticleLayout().getContent()).isEqualTo(content);
            assertThat(b.getAuthor()).isEqualTo("test");
        });
    }

    @Test
    void update(){
        String title   = "제목";
        String content = "test";

        ArticleLayout articleLayout = new ArticleLayout(title, content);

        Board board = boardRepository.save(Board.builder()
                                                .articleLayout(articleLayout)
                                                .author("test")
                                                .build());
        board.setAuthor("author");

        assertThat(board.getAuthor()).isEqualTo("author");
    }

    @Test
    void delete(){
        String title   = "제목";
        String content = "test";

        ArticleLayout articleLayout = new ArticleLayout(title, content);

        Board board = boardRepository.save(Board.builder()
                                    .articleLayout(articleLayout)
                                     .author("test")
                                     .build());
        Long boardId = board.getId();

        boardRepository.delete(board);

        Optional<Board> deleteBoard = boardRepository.findById(boardId);
        assertFalse(deleteBoard.isPresent());
    }
}