package com.lsj.board.web;

import com.lsj.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(IndexController.class)
@AutoConfigureMockMvc
class IndexControllerTest {

    @MockBean
    private BoardService boardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void updateBoardTest() throws Exception{
        mockMvc.perform(get("/updateBoard/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}