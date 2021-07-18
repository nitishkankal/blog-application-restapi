package com.continental.blogapplication.blogmanagement.dto;

import com.continental.blogapplication.blogmanagement.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDto {
    private Integer id;
    private String title;
    private String url;

}
