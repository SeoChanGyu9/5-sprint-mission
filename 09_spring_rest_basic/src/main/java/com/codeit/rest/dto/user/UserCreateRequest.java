package com.codeit.rest.dto.user;

import com.codeit.rest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private LocalDate birthday;

    public User toUser(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .birthday(birthday)
                .build();

    }

}
