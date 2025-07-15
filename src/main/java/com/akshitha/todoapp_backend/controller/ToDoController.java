package com.akshitha.todoapp_backend.controller;

import com.akshitha.todoapp_backend.dto.ToDoDto;
import com.akshitha.todoapp_backend.service.ToDoService;
import com.akshitha.todoapp_backend.service.UserServiceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private ToDoService toDoService;

    private UserServiceImpl userServiceImpl;

    //Add Task
    @PostMapping("/user/{username}")
    public ResponseEntity<ToDoDto> createTaskForUser(@PathVariable String username, @RequestBody ToDoDto toDoDto) {
        ToDoDto savedTask = toDoService.createToDoForUser(username, toDoDto);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ToDoDto>> getTasksByUsername(@PathVariable String username) {
        List<ToDoDto> tasks = toDoService.getTasksByUsername(username);
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/user/{username}/id/{id}")
    public ResponseEntity<ToDoDto> getTaskById(@PathVariable String username, @PathVariable Long id) {
        ToDoDto task = toDoService.findTaskById(id, username);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/user/{username}/id/{id}")
    public ResponseEntity<ToDoDto> updateTask(@PathVariable String username,
                                              @PathVariable Long id,
                                              @RequestBody ToDoDto updatedDto) {
        ToDoDto updatedTask = toDoService.updateTask(id, username, updatedDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/user/{username}/id/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String username, @PathVariable Long id) {
        toDoService.deleteById(id, username);
        return ResponseEntity.ok("Deleted Task");
    }


}
