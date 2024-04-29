package com.example.Demo.Basic.Authentication.dto;

import com.example.Demo.Basic.Authentication.statval.UserRole;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserData {

    private String username;
    private String email;
    private String fullName;
    private String password;
    private UserRole appUserRole;
}
