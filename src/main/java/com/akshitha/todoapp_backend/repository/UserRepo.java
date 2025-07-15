package com.akshitha.todoapp_backend.repository;

import com.akshitha.todoapp_backend.entity.UserToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserToDo, Long> {
    Optional<UserToDo> findByUsername(String username);
}
