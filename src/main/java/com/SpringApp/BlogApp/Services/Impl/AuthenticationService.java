package com.SpringApp.BlogApp.Services.Impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringApp.BlogApp.Entity.User;
import com.SpringApp.BlogApp.Repositories.UserRepo;
import com.SpringApp.BlogApp.Security.LoginUserDto;
import com.SpringApp.BlogApp.Security.RegisterUserDto;



@Service
public class AuthenticationService {
	
private final UserRepo userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepo userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user =  User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .about(input.getAbout())
                .password(passwordEncoder.encode(input.getPassword())).build();

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}
