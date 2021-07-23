package com.continental.blogapplication.blogmanagement.service.impl;

import com.continental.blogapplication.blogmanagement.dto.BlogPostDto;
import com.continental.blogapplication.blogmanagement.dto.PhotoDto;
import com.continental.blogapplication.blogmanagement.entity.PhotoEntity;
import com.continental.blogapplication.blogmanagement.entity.PostEntity;
import com.continental.blogapplication.blogmanagement.exception.BlogPostNotFoundException;
import com.continental.blogapplication.blogmanagement.exception.PhotoNotFoundException;
import com.continental.blogapplication.blogmanagement.mapper.EntityDtoMapper;
import com.continental.blogapplication.blogmanagement.repository.PhotoRepository;
import com.continental.blogapplication.blogmanagement.service.PhotoService;
import com.continental.blogapplication.common.response.SpringApiResponse;
import com.continental.blogapplication.usermanagement.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    EntityDtoMapper entityDtoMapper;


    @Override
    @Transactional
    public List<PhotoDto> getAllPhotos() {
        List<PhotoEntity> posts = photoRepository.findAll();
        return posts.stream().map(entityDtoMapper::mapFromPhotoEntityToPhotoDto).collect(toList());
    }

    @Override
    @Transactional
    public ResponseEntity<PhotoEntity> createPhoto(PhotoDto photoDto) {
        PhotoEntity photoEntity = entityDtoMapper.mapFromPhotoDtoToPhotoEntity(photoDto);
        PhotoEntity newphotoEntity  = photoRepository.save(photoEntity);
        return new ResponseEntity<PhotoEntity>(newphotoEntity, HttpStatus.OK);

    }

    @Override
    public PhotoDto fetchPhotoById(Integer id) {
        PhotoEntity post = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(id));
        return entityDtoMapper.mapFromPhotoEntityToPhotoDto(post);
    }

    @Override
    public PhotoEntity updatePhoto(Integer id, PhotoDto photoDto) {
        PhotoEntity photoEntity = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(id));
        entityDtoMapper.mapFromPhotoDtoToPhotoEntity(photoDto, photoEntity);
        photoEntity.setId(id);
        photoRepository.save(photoEntity);
        return photoEntity;
    }


    @Override
    public SpringApiResponse deletePost(Integer id) {
        PhotoEntity photoEntity = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(id));
        if(!Objects.isNull(photoEntity))
            photoRepository.deleteById(id);
        return new SpringApiResponse(Boolean.TRUE, "You successfully deleted post");


    }
}
