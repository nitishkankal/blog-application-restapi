package com.continental.blogapplication.blogmanagement.mapper;

import com.continental.blogapplication.blogmanagement.dto.BlogPostDto;
import com.continental.blogapplication.blogmanagement.dto.PhotoDto;
import com.continental.blogapplication.blogmanagement.entity.PhotoEntity;
import com.continental.blogapplication.blogmanagement.entity.PostEntity;
import com.continental.blogapplication.usermanagement.entity.UsersEntity;
import com.continental.blogapplication.usermanagement.repository.UsersRepository;
import com.continental.blogapplication.usermanagement.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.util.stream.Collectors.toList;

@Service
public class EntityDtoMapper {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private UserAuthService userAuthService;

    public PhotoDto mapFromPhotoEntityToPhotoDto(PhotoEntity photoEntity) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setId(photoEntity.getId());
        photoDto.setTitle(photoEntity.getTitle());
        photoDto.setUrl(photoEntity.getUrl());
        return photoDto;
    }

    public PhotoEntity mapFromPhotoDtoToPhotoEntity(PhotoDto photoDto) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(photoDto.getId());
        photoEntity.setTitle(photoDto.getTitle());
        photoEntity.setUrl(photoDto.getUrl());
        return photoEntity;
    }
    public PhotoEntity mapFromPhotoDtoToPhotoEntity(PhotoDto photoDto,PhotoEntity photoEntity) {
        photoEntity.setId(photoDto.getId());
        photoEntity.setTitle(photoDto.getTitle());
        photoEntity.setUrl(photoDto.getUrl());
        return photoEntity;
    }


    public BlogPostDto mapFromPostEntityToDto(PostEntity postEntity) {
        BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setId(postEntity.getId());
        blogPostDto.setTitle(postEntity.getTitle());
        blogPostDto.setBody(postEntity.getBody());
        blogPostDto.setUsername(postEntity.getUsersEntity().getUsername());
        blogPostDto.setPhotos(postEntity.getPhoto().stream().map(this::mapFromPhotoEntityToPhotoDto).collect(toList()));
        return blogPostDto;
    }

    public PostEntity mapFromDtoToPostEntity(BlogPostDto blogPostDto,PostEntity postEntity) {
        User loggedInUser = userAuthService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        UsersEntity user = userRepository.findByUsername(blogPostDto.getUsername())
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found -" + blogPostDto.getUsername()));



        postEntity.setTitle(blogPostDto.getTitle());
        postEntity.setBody(blogPostDto.getBody());
        postEntity.setUsersEntity(user);
        postEntity.setPhoto(blogPostDto.getPhotos().stream().map(this::mapFromPhotoDtoToPhotoEntity).collect(toList()));
        postEntity.setCreatedAt(Instant.now());
        postEntity.setUpdatedAt(Instant.now());
        return postEntity;
    }
}
