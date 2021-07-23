package com.continental.blogapplication.blogmanagement.service;

import com.continental.blogapplication.blogmanagement.dto.PhotoDto;
import com.continental.blogapplication.blogmanagement.entity.PhotoEntity;
import com.continental.blogapplication.common.response.SpringApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhotoService {

    @Transactional
    List<PhotoDto> getAllPhotos();

    ResponseEntity<PhotoEntity> createPhoto(PhotoDto photoDto);

    PhotoDto fetchPhotoById(Integer id);

    PhotoEntity updatePhoto(Integer id, PhotoDto photoDto);

    SpringApiResponse deletePost(Integer id);
}
