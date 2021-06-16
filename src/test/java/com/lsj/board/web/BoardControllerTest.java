package com.lsj.board.web;

import com.lsj.board.domain.board.Board;
import com.lsj.board.domain.board.BoardRepository;
import com.lsj.board.web.dto.BoardSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoardControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @AfterEach
    public void cleanAll() throws Exception{
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("BoardController Select")
    public void selectBoards() throws Exception{
        String title = "title";
        String content = "content";
        String author = "author";


        Board board = Board.builder()
                           .title(title)
                           .content(content)
                           .author(author)
                           .build();

        boardRepository.save(board);
        List<Board> boards = boardRepository.findAll();

        assertThat(boards.get(0).getTitle()).isEqualTo(title);
        assertThat(boards.get(0).getAuthor()).isEqualTo(author);
        assertThat(boards.get(0).getContent()).isEqualTo(content);
    }


    @Test
    @DisplayName("BoardController Insert")
    public void insertDatas() throws Exception{
        //given
        String title   = "title";
        String content = "content";
        String author  = "author";

        BoardSaveRequestDto dto = BoardSaveRequestDto.builder()
                                                     .title(title)
                                                     .content(content)
                                                     .author(author)
                                                     .build();

        String url = "http://localhost:" + port + "/api/v1/board/save";
        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, dto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> list = boardRepository.findAll();

        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getContent()).isEqualTo(content);
        assertThat(list.get(0).getAuthor()).isEqualTo(author);
    }
}