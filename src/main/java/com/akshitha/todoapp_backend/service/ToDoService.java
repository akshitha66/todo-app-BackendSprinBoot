package com.akshitha.todoapp_backend.service;

import com.akshitha.todoapp_backend.dto.ToDoDto;

import java.util.List;

public interface ToDoService {

    ToDoDto createToDo(ToDoDto toDoDto);

    // ✅ Create task by username
    ToDoDto createToDoForUser(String username, ToDoDto toDoDto);

    // ✅ Get task by task ID and username (ensure user owns the task)
    ToDoDto findTaskById(Long id, String username);

    // ✅ Get all tasks (admin/global view)
    List<ToDoDto> findAll();

    // ✅ Get all tasks for a specific user
    List<ToDoDto> getTasksByUsername(String username);

    // ✅ Update task by ID and username (ensures correct user)
    ToDoDto updateTask(Long id, String username, ToDoDto updatedToDoDto);

    // ✅ Delete task by ID and username
    void deleteById(Long id, String username);

    // ✅ Delete all tasks (optional: global, or can filter by user if needed)
    void deleteAll(ToDoDto toDoDto);
}
