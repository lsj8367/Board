package com.lsj.board.service;

import com.lsj.board.domain.user.User;
import com.lsj.board.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long saveUser(User user) {
        return userRepository.save(user).getId();
    }
}
