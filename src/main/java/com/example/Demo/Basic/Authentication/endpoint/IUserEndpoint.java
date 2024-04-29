package com.example.Demo.Basic.Authentication.endpoint;

import com.example.Demo.Basic.Authentication.dto.LoginData;
import com.example.Demo.Basic.Authentication.dto.RequestUserData;
import com.example.Demo.Basic.Authentication.entity.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/user",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public interface IUserEndpoint {

    @GetMapping()
    ResponseEntity<List<User>> getDataUser();

    @PostMapping("/register")
    ResponseEntity<User> registerUser(@RequestBody RequestUserData user);

    @PostMapping("/login")
    ResponseEntity<String> loginUser(@RequestBody LoginData loginData);
}
