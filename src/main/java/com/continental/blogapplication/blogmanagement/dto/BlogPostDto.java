package com.continental.blogapplication.blogmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {

    private Integer id;

    private String title;

    private String body;

    private String username;

    private List<PhotoDto> photos;

}
