package com.lsj.board.web.dto;

import lombok.Builder;
import lombok.Setter;

@Setter
public class UserUpdateRequestDto {
    private String userId;
    private String password;

    @Builder
    public UserUpdateRequestDto(String userId, String password){
        this.userId = userId;
        this.password = password;
    }
}
