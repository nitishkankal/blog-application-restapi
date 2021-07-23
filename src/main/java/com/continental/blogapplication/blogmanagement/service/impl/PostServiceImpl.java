package com.continental.blogapplication.blogmanagement.service.impl;

import com.continental.blogapplication.blogmanagement.dto.BlogPostDto;
import com.continental.blogapplication.blogmanagement.dto.PhotoDto;
import com.continental.blogapplication.blogmanagement.entity.PhotoEntity;
import com.continental.blogapplication.blogmanagement.entity.PostEntity;
import com.continental.blogapplication.blogmanagement.exception.BlogPostNotFoundException;
import com.continental.blogapplication.blogmanagement.mapper.EntityDtoMapper;
import com.continental.blogapplication.blogmanagement.repository.PostRepository;
import com.continental.blogapplication.blogmanagement.service.PostService;
import com.continental.blogapplication.common.response.SpringApiResponse;
import com.continental.blogapplication.usermanagement.entity.UsersEntity;
import com.continental.blogapplication.usermanagement.repository.UsersRepository;
import com.continental.blogapplication.usermanagement.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    EntityDtoMapper entityDtoMapper;

    @Override
    @Transactional
    public List<BlogPostDto> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream().map(entityDtoMapper::mapFromPostEntityToDto).collect(toList());
    }

    @Override
    @Transactional
    public ResponseEntity<PostEntity> createPost(BlogPostDto BlogPostDto) {
        PostEntity postEntity = new PostEntity();
        entityDtoMapper.mapFromDtoToPostEntity(BlogPostDto,postEntity);
        PostEntity newPostEntity =  postRepository.save(postEntity);
        return new ResponseEntity<PostEntity>(newPostEntity, HttpStatus.OK);
    }

    @Override
    public BlogPostDto fetchPostById(Integer id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new BlogPostNotFoundException(" Blog not found For id - " + id));
        return entityDtoMapper.mapFromPostEntityToDto(post);
    }

    @Override
    public PostEntity updatePost(Integer id, BlogPostDto blogPostDto) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new BlogPostNotFoundException(" Blog not found For id - " + id));
        entityDtoMapper.mapFromDtoToPostEntity(blogPostDto,postEntity);
        postRepository.save(postEntity);
       return postEntity;
    }


    @Override
    public SpringApiResponse deletePost(Integer id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new BlogPostNotFoundException(" Blog not found For id - " + id));
        User loggedInUser = userAuthService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        UsersEntity entityUser = userRepository.findByUsername(post.getUsersEntity().getUsername())
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found -" + post.getUsersEntity().getUsername()));
        if (loggedInUser.getUsername().equals(entityUser.getUsername())) {
            postRepository.deleteById(id);
            return new SpringApiResponse(Boolean.TRUE, "You successfully deleted post");
        }

        return new SpringApiResponse(Boolean.FALSE, "You don't have permission to delete this post");

    }


}
