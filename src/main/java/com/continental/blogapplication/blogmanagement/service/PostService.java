package com.continental.blogapplication.blogmanagement.service;

import com.continental.blogapplication.blogmanagement.dto.BlogPostDto;
import com.continental.blogapplication.blogmanagement.entity.PostEntity;
import com.continental.blogapplication.common.response.SpringApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    List<BlogPostDto> getAllPosts();

    ResponseEntity<PostEntity> createPost(BlogPostDto BlogPostDto);

    BlogPostDto fetchPostById(Integer id);

    PostEntity updatePost(Integer id, BlogPostDto blogPostDto);

    SpringApiResponse deletePost(Integer id);
}
