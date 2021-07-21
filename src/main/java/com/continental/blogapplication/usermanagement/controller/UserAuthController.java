package com.continental.blogapplication.usermanagement.controller;


import com.continental.blogapplication.common.response.SpringApiResponse;
import com.continental.blogapplication.usermanagement.dto.AuthResponseDto;
import com.continental.blogapplication.usermanagement.dto.LoginDto;
import com.continental.blogapplication.usermanagement.dto.RegisterDto;
import com.continental.blogapplication.usermanagement.repository.UsersRepository;
import com.continental.blogapplication.usermanagement.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    UsersRepository userRepository;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginDto loginReqObj) {

        return userAuthService.login(loginReqObj);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerReqObj) {
        if (userRepository.existsByUsername(registerReqObj.getUsername())) {
            return new ResponseEntity(new SpringApiResponse(false,"User Already Exist!, pls use login"),HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(registerReqObj.getEmail())) {
            return new ResponseEntity(new SpringApiResponse(false,"Email Already registered, pls login  !"),HttpStatus.BAD_REQUEST);
        }
        userAuthService.registerUser(registerReqObj);
        return new ResponseEntity(new SpringApiResponse(true,"Successfully registered!"),HttpStatus.OK);
    }

}
