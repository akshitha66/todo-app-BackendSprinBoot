package com.akshitha.todoapp_backend.repository;

import com.akshitha.todoapp_backend.entity.Todo;
import com.akshitha.todoapp_backend.entity.UserToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepo extends JpaRepository<Todo, Long> {
    List<Todo> findByUserToDo(UserToDo userToDo);
}
