package com.continental.blogapplication.blogmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blogapp/")
public class BlogAppController {

    @GetMapping("/all")
    public ResponseEntity showAllPosts() {
        return new ResponseEntity("Hello", HttpStatus.OK);
    }

}
