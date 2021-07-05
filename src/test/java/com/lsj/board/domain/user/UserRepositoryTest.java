package com.lsj.board.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void cleanDatas(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("생성")
    @Transactional
    void create(){
        User user = userRepository.save(User.builder()
                                  .userId("홍길동")
                                  .password("123")
                                  .build());

        assertNotNull(user);
    }

    @Test
    @DisplayName("유저 테스트")
    void loginTest(){
        String userId = "aaa";
        String password = "123";
        User user = userRepository.save(User.builder()
                                            .userId(userId)
                                            .password(password)
                                            .build());

        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    @Transactional
    void update(){
        User user = userRepository.save(User.builder()
                                  .userId("abc")
                                  .password("123")
                                  .build());

        user.setUserId("가나다");
        user.setPassword("456");

        assertThat(user.getUserId()).isEqualTo("가나다");
        assertThat(user.getPassword()).isEqualTo("456");
    }

    @Test
    @Transactional
    void delete(){
        User user = userRepository.save(User.builder()
                .userId("abc")
                .password("123")
                .build());

        Long getId = user.getId();
        userRepository.delete(user);

        Optional<User> deleteUser = userRepository.findById(getId);
        //무조건 false
        assertFalse(deleteUser.isPresent());
    }
}