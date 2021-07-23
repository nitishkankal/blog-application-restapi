package com.continental.blogapplication.blogmanagement.repository;

import com.continental.blogapplication.blogmanagement.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
}
