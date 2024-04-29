package com.example.Demo.Basic.Authentication.service.impl;

import com.example.Demo.Basic.Authentication.config.JwtTokenUtil;
import com.example.Demo.Basic.Authentication.dto.LoginData;
import com.example.Demo.Basic.Authentication.entity.User;
import com.example.Demo.Basic.Authentication.repository.IUserRepository;
import com.example.Demo.Basic.Authentication.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User registerNewUser(User user) {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if (userExists) {
            throw new RuntimeException("User already exists");
        }

        String encoderPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found"));
    }

    public ResponseEntity<String> loginUser(LoginData loginData) {
        User user = userRepository.findByUsername(loginData.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        if (user != null && bCryptPasswordEncoder.matches(loginData.getPassword(), user.getPassword())) {
            // User is logged in successfully
            // Generate an access token
            String token = jwtTokenUtil.generateToken(user);

            // Return the access token in the response
            return ResponseEntity.ok().body(token);
        }

        return ResponseEntity.badRequest().body("Invalid username or password!");
    }
}
