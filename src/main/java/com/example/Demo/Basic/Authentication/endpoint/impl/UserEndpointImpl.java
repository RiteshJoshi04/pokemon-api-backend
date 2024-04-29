package com.example.Demo.Basic.Authentication.endpoint.impl;

import com.example.Demo.Basic.Authentication.dto.LoginData;
import com.example.Demo.Basic.Authentication.dto.RequestUserData;
import com.example.Demo.Basic.Authentication.endpoint.IUserEndpoint;
import com.example.Demo.Basic.Authentication.entity.User;
import com.example.Demo.Basic.Authentication.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UserEndpointImpl implements IUserEndpoint {

    private final IUserService userService;

    @Override
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("!isAuthenticated()")
    public ResponseEntity<List<User>> getDataUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<User> registerUser(RequestUserData request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.registerNewUser(user));
    }

    @Override
    public ResponseEntity<String> loginUser(LoginData loginData) {
        return userService.loginUser(loginData);
    }
}
