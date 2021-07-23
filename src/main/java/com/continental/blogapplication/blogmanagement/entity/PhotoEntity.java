package com.continental.blogapplication.blogmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photos")
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_photos_id", referencedColumnName ="id")
    private PostEntity postEntity;

    @JsonIgnore
    public PostEntity getPostEntity() {
        return postEntity;
    }

}
