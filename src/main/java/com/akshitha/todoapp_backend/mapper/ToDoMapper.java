package com.akshitha.todoapp_backend.mapper;

import com.akshitha.todoapp_backend.dto.ToDoDto;
import com.akshitha.todoapp_backend.entity.Todo;

public class ToDoMapper {

    public static ToDoDto mapToToDoDto(Todo todo) {
        return new ToDoDto(
                todo.getUserToDo() != null ? todo.getUserToDo().getId() : null,
                todo.getId(),
                todo.getTask(),
                todo.getDateTime(),
                todo.getStatus()
        );
    }

    public static Todo mapToToDo(ToDoDto toDoDto) {
        Todo todo = new Todo();
        todo.setId(toDoDto.getId());
        todo.setTask(toDoDto.getTask());
        todo.setDateTime(toDoDto.getDateTime());
        todo.setStatus(toDoDto.getStatus());
        // user will be set in service layer based on userId
        return todo;
    }
}
