package com.lsj.board.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsj.board.domain.board.ArticleLayout;
import com.lsj.board.domain.board.Board;
import com.lsj.board.service.BoardService;
import com.lsj.board.web.dto.BoardSaveRequestDto;
import com.lsj.board.web.dto.BoardUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @MockBean
    private BoardService boardService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                                      .alwaysDo(print()) //항상 HTTP 통신 request, response 결과 반환
                                      .addFilters(new CharacterEncodingFilter("UTF-8", true)) //utf8설정
                                      .build();
    }

    @AfterEach
    void cleanAll() throws Exception{
        boardService.deleteAll();
    }

    @Test
    @DisplayName("BoardController Select")
    void selectBoards() throws Exception{
        //given
        String title   = "제목";
        String content = "test";
        String author = "author";

        ArticleLayout articleLayout = new ArticleLayout(title, content);


        Board board = Board.builder()
                           .articleLayout(articleLayout)
                           .author(author)
                           .build();
        //when
        mockMvc.perform(get("/api/v1/board")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(board)))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("글 상세보기")
    void selectOne() throws Exception{
        mockMvc.perform(post("/api/v1/board/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("BoardController Insert")
    void insertDatas() throws Exception{
        String title   = "title";
        String content = "content";
        String author  = "author";

        BoardSaveRequestDto dto = BoardSaveRequestDto.builder()
                                                     .title(title)
                                                     .content(content)
                                                     .author(author).build();

        mockMvc.perform(post("/api/v1/board/save").contentType(MediaType.APPLICATION_JSON)
                                                            .content(new ObjectMapper().writeValueAsString(dto)))
                                                            .andExpect(status().isOk());
    }

    @Test
    void updateBoard() throws Exception{
        String updateTitle   = "asdklaj";
        String updateContent = "asdhaskdj";

        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                                                                .title(updateTitle)
                                                                .content(updateContent)
                                                                .build();

        mockMvc.perform(put("/api/v1/board/"  + 1)
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(requestDto)))
               .andExpect(status().isOk());
    }

    @Test
    void deleteBoard() throws Exception{
        mockMvc.perform(delete("/api/v1/board/" + 1)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());

    }
}