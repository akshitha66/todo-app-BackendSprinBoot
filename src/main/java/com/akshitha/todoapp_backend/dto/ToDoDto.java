package com.akshitha.todoapp_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto {
    private Long userId;
    private long id;
    private String task;
    private LocalDateTime dateTime;
    private String status;
}
