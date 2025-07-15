package com.akshitha.todoapp_backend.controller;

import com.akshitha.todoapp_backend.dto.UserDto;
import com.akshitha.todoapp_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUser  = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("myId") Long id){
        UserDto getById  = userService.findUserById(id);
        return ResponseEntity.ok(getById);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> getAll = userService.getAll();
        return ResponseEntity.ok(getAll);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("myId") Long id, @RequestBody  UserDto updatedUserDto){
        UserDto updatedUser = userService.updateTask(id, updatedUserDto);
        return ResponseEntity.ok(updatedUser);

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("myId") Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("Deleted User");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllUsers(UserDto userDto){
        userService.deleteAll(userDto);
        return ResponseEntity.ok("Delete All Users");
    }


}
