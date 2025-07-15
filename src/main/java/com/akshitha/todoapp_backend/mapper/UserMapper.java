package com.akshitha.todoapp_backend.mapper;

import com.akshitha.todoapp_backend.dto.UserDto;
import com.akshitha.todoapp_backend.entity.UserToDo;

import java.util.Locale;

public class UserMapper {
    public static UserDto mapToUserDto(UserToDo usertoDo){
        return new UserDto(
                usertoDo.getRole(),
                usertoDo.getId(),
                usertoDo.getUsername(),
                usertoDo.getEmail(),
                usertoDo.getPassword()

        );
    }

    public static UserToDo mapToUser(UserDto userDto){
        return new UserToDo(
                userDto.getRole(),
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                null
        );
    }
}
