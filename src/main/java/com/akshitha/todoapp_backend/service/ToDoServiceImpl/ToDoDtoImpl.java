package com.akshitha.todoapp_backend.service.ToDoServiceImpl;

import com.akshitha.todoapp_backend.dto.ToDoDto;
import com.akshitha.todoapp_backend.dto.UserDto;
import com.akshitha.todoapp_backend.entity.Todo;
import com.akshitha.todoapp_backend.entity.UserToDo;
import com.akshitha.todoapp_backend.exception.ResourceNotFoundException;
import com.akshitha.todoapp_backend.mapper.ToDoMapper;
import com.akshitha.todoapp_backend.repository.ToDoRepo;
import com.akshitha.todoapp_backend.repository.UserRepo;
import com.akshitha.todoapp_backend.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoDtoImpl implements ToDoService {

    @Autowired
    private ToDoRepo toDoRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public ToDoDto createToDo(ToDoDto toDoDto) {
        // Fetch UserToDo from DB by userId in DTO
        UserToDo user = userRepo.findById(toDoDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + toDoDto.getUserId()));

        // Map DTO to entity
        Todo toDo = ToDoMapper.mapToToDo(toDoDto);

        // Set the user
        toDo.setUserToDo(user);

        // Set current time
        toDo.setDateTime(LocalDateTime.now());

        // Save to DB
        Todo saved = toDoRepo.save(toDo);

        // Map back to DTO and return
        return ToDoMapper.mapToToDoDto(saved);
    }

    @Override
    public ToDoDto createToDoForUser(String username, ToDoDto toDoDto) {
        UserToDo user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Todo todo = ToDoMapper.mapToToDo(toDoDto);
        todo.setUserToDo(user);  // associate task with user
        todo.setDateTime(LocalDateTime.now());

        Todo saved = toDoRepo.save(todo);
        return ToDoMapper.mapToToDoDto(saved);
    }


    @Override
    public ToDoDto findTaskById(Long id, String username) {
        UserToDo user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Todo todo = toDoRepo.findById(id)
                .filter(t -> t.getUserToDo().getId().equals(user.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this user"));

        return ToDoMapper.mapToToDoDto(todo);
    }


    @Override
    public List<ToDoDto> findAll() {
        List<Todo> findAllTasks = toDoRepo.findAll();

        return findAllTasks.stream()
                .map(ToDoMapper::mapToToDoDto)
                .collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateTask(Long id, String username, ToDoDto updatedDto) {
        UserToDo user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Todo todo = toDoRepo.findById(id)
                .filter(t -> t.getUserToDo().getId().equals(user.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this user"));

        todo.setTask(updatedDto.getTask());
        todo.setStatus(updatedDto.getStatus());
        todo.setDateTime(LocalDateTime.now());

        Todo updated = toDoRepo.save(todo);
        return ToDoMapper.mapToToDoDto(updated);
    }

    @Override
    public void deleteById(Long id, String username) {
        UserToDo user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Todo todo = toDoRepo.findById(id)
                .filter(t -> t.getUserToDo().getId().equals(user.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this user"));

        toDoRepo.delete(todo);
    }

    @Override
    public void deleteAll(ToDoDto toDoDto) {
        toDoRepo.deleteAll();
    }

    @Override
    public List<ToDoDto> getTasksByUsername(String username) {
       UserToDo user = userRepo.findByUsername(username)
               .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Todo> todos = toDoRepo.findByUserToDo(user);

        return todos.stream()
                .map(ToDoMapper::mapToToDoDto)
                .collect(Collectors.toList());

    }


}
