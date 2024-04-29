package com.example.Demo.Basic.Authentication.service;

import com.example.Demo.Basic.Authentication.dto.LoginData;
import com.example.Demo.Basic.Authentication.entity.User;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IUserService {

    User registerNewUser(User user);

    List<User> findAll();

    ResponseEntity<String> loginUser(LoginData loginData);
}
