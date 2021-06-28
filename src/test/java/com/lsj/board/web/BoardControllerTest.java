package com.lsj.board.web;

import com.lsj.board.domain.board.Board;
import com.lsj.board.domain.board.BoardRepository;
import com.lsj.board.web.dto.BoardSaveRequestDto;
import com.lsj.board.web.dto.BoardUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //내장 톰캣 사용으로 RestTemplate사용가능
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) //MOCK객체 환경
//@AutoConfigureMockMvc
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
    @DisplayName("글 상세보기")
    void selectOne() throws Exception{
        Board board = Board.builder()
                           .title("제목")
                           .content("내용")
                           .author("작성자")
                           .build();
        boardRepository.save(board);

        Optional<Board> optBoard = boardRepository.findById(board.getId());
        Board resultBoard = optBoard.orElseThrow(
                            () -> new NoSuchElementException("게시글이 없음")
                            );
        assertThat(resultBoard.getTitle()).isEqualTo("제목");
        assertThat(resultBoard.getContent()).isEqualTo("내용");
        assertThat(resultBoard.getAuthor()).isEqualTo("작성자");
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

    @Test
    void updateBoard() throws Exception{
        Board updateBoard = boardRepository.save(Board.builder()
                                                      .title("title")
                                                      .content("content")
                                                      .author("author")
                                                      .build());

        Long updateId = updateBoard.getId();

        String updateTitle = "asdklaj";
        String updateContent = "asdhaskdj";

        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                                                                .title(updateTitle)
                                                                .content(updateContent)
                                                                .build();

        String url = "http://localhost:" + port + "/api/v1/board/" + updateId;
        HttpEntity<BoardUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all = boardRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        assertThat(all.get(0).getContent()).isEqualTo(updateContent);

    }
}