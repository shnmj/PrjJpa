package com.green.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class UserDto {   // AddUserRequest -> UserDto
    private String email;
    private String password;
}
