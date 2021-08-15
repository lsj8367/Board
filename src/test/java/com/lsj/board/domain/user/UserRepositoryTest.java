package com.lsj.board.domain.user;

import com.lsj.board.config.QuerydslConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(QuerydslConfiguration.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("생성")
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

    @Test
    void findByName() {
        User user = userRepository.save(User.builder()
                        .name("홍길동")
                        .userId("abc")
                        .password("123")
                        .build());

        User result = userRepository.findByName("홍길동");

        assertThat(result.getName()).isEqualTo("홍길동");
    }
}