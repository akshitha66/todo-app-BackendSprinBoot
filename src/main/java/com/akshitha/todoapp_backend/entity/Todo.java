package com.akshitha.todoapp_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "Task")
    private String task;
    @Column(name = "Date_Time")
    private LocalDateTime dateTime;
    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserToDo userToDo;

}
