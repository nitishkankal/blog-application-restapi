package com.continental.blogapplication.blogmanagement.controller;


import com.continental.blogapplication.blogmanagement.dto.BlogPostDto;
import com.continental.blogapplication.blogmanagement.dto.PhotoDto;
import com.continental.blogapplication.blogmanagement.entity.PhotoEntity;
import com.continental.blogapplication.blogmanagement.entity.PostEntity;
import com.continental.blogapplication.blogmanagement.service.PhotoService;
import com.continental.blogapplication.blogmanagement.service.PostService;
import com.continental.blogapplication.common.response.SpringApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogapp/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping(value = "/createPhoto")
    public ResponseEntity<PhotoEntity> createPost(@RequestBody PhotoDto photoDto) {
       return photoService.createPhoto(photoDto);
       // return new ResponseEntity(new SpringApiResponse(true,"Successfully Added Photo!"), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhotoDto>> showAllPhotos() {
        return new ResponseEntity<>(photoService.getAllPhotos(), HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<PhotoDto> getPost(@PathVariable @RequestBody Integer id) {
        return new ResponseEntity<>(photoService.fetchPhotoById(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PhotoEntity> updatePost(@PathVariable(name = "id") Integer id, @RequestBody PhotoDto photoDto ) {
        PhotoEntity photo = photoService.updatePhoto(id, photoDto);

        return new ResponseEntity< >(photo, HttpStatus.OK);
    }
    @PostMapping("delete/{id}")
    public ResponseEntity<SpringApiResponse> deletePost(@PathVariable(name = "id") Integer id) {
        SpringApiResponse apiResponse = photoService.deletePost(id);
        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }
}
