package com.lsj.board.web.dto;

import com.lsj.board.domain.user.User;
import lombok.Builder;
import lombok.Setter;

@Setter
public class UserSaveRequestDto {
    private String userId;
    private String password;

    @Builder
    public UserSaveRequestDto(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    public User toEntity(){
        return User.builder().userId(userId).password(password).build();
    }
}
