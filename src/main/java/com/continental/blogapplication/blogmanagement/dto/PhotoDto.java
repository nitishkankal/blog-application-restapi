package com.continental.blogapplication.blogmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDto {
    private Integer id;
    private String title;
    private String url;

}
