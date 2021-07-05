package com.lsj.board.web;

import com.lsj.board.domain.user.User;
import com.lsj.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/user")
    public Long saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
