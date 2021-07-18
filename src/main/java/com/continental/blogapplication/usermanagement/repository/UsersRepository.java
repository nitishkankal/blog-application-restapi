package com.continental.blogapplication.usermanagement.repository;

import com.continental.blogapplication.usermanagement.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer>, JpaSpecificationExecutor<UsersEntity> {
    Optional<UsersEntity> getByUsername(@NotBlank String username);

    Optional<UsersEntity> getByEmail(@NotBlank String email);

    Boolean isUserExistbyUsername(@NotBlank String username);

    Boolean isUserExistbyEmail(@NotBlank String email);

    Optional<UsersEntity> getByUsernameOrEmail(String username, String email);



}
