package com.akshitha.todoapp_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/all-users")
    public ResponseEntity<String> getAllUserData() {
        return ResponseEntity.ok("Only admin can access this!");
    }
}
