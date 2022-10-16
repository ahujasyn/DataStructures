package com.nesstraining.simpletokendemo.repo;

import com.nesstraining.simpletokendemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User>findUserByUsername(String username);
}
