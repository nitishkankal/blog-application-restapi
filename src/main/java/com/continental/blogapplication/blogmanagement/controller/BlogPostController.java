package com.continental.blogapplication.blogmanagement.controller;

import com.continental.blogapplication.blogmanagement.dto.BlogPostDto;
import com.continental.blogapplication.blogmanagement.entity.PostEntity;
import com.continental.blogapplication.blogmanagement.service.PostService;
import com.continental.blogapplication.common.response.SpringApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogapp/posts")
public class BlogPostController {
    @Autowired
    private PostService postService;

    @PostMapping(value = "/createPost")
    public ResponseEntity createPost(@RequestBody BlogPostDto postDto) {
        return  postService.createPost(postDto);
        //return new ResponseEntity(new SpringApiResponse(true,"Successfully Created Post!"),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogPostDto>> showAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BlogPostDto> getPost(@PathVariable @RequestBody Integer id) {
        return new ResponseEntity<>(postService.fetchPostById(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PostEntity> updatePost(@PathVariable(name = "id") Integer id, @RequestBody BlogPostDto postDto ) {
        PostEntity post = postService.updatePost(id, postDto);

        return new ResponseEntity< >(post, HttpStatus.OK);
    }
    @PostMapping("delete/{id}")
    public ResponseEntity<SpringApiResponse> deletePost(@PathVariable(name = "id") Integer id) {
        SpringApiResponse apiResponse = postService.deletePost(id);
        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }
}
