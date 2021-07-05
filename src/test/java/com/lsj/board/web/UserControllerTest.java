package com.lsj.board.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsj.board.domain.user.User;
import com.lsj.board.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                                      .addFilters(new CharacterEncodingFilter("UTF-8", true))
                                      .alwaysDo(print())
                                      .build();
    }

    @Test
    void save() throws Exception{
        //given
        String userId = "lsj";
        String password = "123";
        User user = User.builder()
                        .userId(userId)
                        .password(password)
                        .build();

        given(userService.saveUser(user)).willReturn(1L);

        Long id = userService.saveUser(user);

        //when
        mockMvc.perform(post("/api/v1/user")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(user)))
               .andExpect(status().isOk());

        //then
        assertThat(id).isEqualTo(1L);
    }
}