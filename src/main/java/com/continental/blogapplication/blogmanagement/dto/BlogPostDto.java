package com.continental.blogapplication.blogmanagement.dto;

import com.continental.blogapplication.usermanagement.entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    private List<PhotoDto> photourls;

}
