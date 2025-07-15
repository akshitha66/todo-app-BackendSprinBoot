package com.akshitha.todoapp_backend.service;

import com.akshitha.todoapp_backend.dto.ToDoDto;
import com.akshitha.todoapp_backend.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto findUserById(Long id);
    List<UserDto> getAll();
    UserDto updateTask(Long id, UserDto updatedUserDto);

    void deleteById(Long id);
    void  deleteAll(UserDto userDto);
}
