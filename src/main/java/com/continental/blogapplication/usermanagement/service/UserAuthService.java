package com.continental.blogapplication.usermanagement.service;

import com.continental.blogapplication.usermanagement.dto.AuthResponseDto;
import com.continental.blogapplication.usermanagement.dto.LoginDto;
import com.continental.blogapplication.usermanagement.dto.RegisterDto;
import com.continental.blogapplication.usermanagement.entity.UsersEntity;
import com.continental.blogapplication.usermanagement.jwt.JwtUtil;
import com.continental.blogapplication.usermanagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public void registerUser(RegisterDto registerDto) {
        UsersEntity user = new UsersEntity();
        user.setUsername(registerDto.getUsername());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encodePassword(registerDto.getPassword()));
        user.setPhone(registerDto.getPhone());
        userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthResponseDto login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtUtil.generateToken(authenticate);
        return new AuthResponseDto(authenticationToken, loginDto.getUsername());
    }

    public Optional<User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
