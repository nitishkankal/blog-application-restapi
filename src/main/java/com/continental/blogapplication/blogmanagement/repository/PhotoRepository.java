package com.continental.blogapplication.blogmanagement.repository;

import com.continental.blogapplication.blogmanagement.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Integer> {
}

