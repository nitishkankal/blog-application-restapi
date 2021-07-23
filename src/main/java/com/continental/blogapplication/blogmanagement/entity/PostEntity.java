package com.continental.blogapplication.blogmanagement.entity;

import com.continental.blogapplication.usermanagement.entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private UsersEntity usersEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade =CascadeType.ALL, orphanRemoval = true)
    private List<PhotoEntity> photo= new ArrayList<>();

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}
