package com.akshitha.todoapp_backend.service.UserServiceImpl;

import com.akshitha.todoapp_backend.dto.UserDto;
import com.akshitha.todoapp_backend.entity.UserToDo;
import com.akshitha.todoapp_backend.exception.ResourceNotFoundException;
import com.akshitha.todoapp_backend.mapper.UserMapper;
import com.akshitha.todoapp_backend.repository.UserRepo;
import com.akshitha.todoapp_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserToDo userToDo = UserMapper.mapToUser(userDto);
        userToDo.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserToDo saved = userRepo.save(userToDo);
        return UserMapper.mapToUserDto(saved);
    }

    @Override
    public UserDto findUserById(Long id) {
        UserToDo byId = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Does Not exist with the given ID :"+ id));
        return UserMapper.mapToUserDto(byId);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserToDo> getAll = userRepo.findAll();
        return getAll.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateTask(Long id, UserDto updatedUserDto) {
        UserToDo existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Does Not exist with the given ID :"+ id));
        existingUser .setUsername(updatedUserDto.getUsername());
        existingUser .setEmail(updatedUserDto.getEmail());
        existingUser .setPassword(updatedUserDto.getPassword());

       UserToDo updatedUser = userRepo.save(existingUser);
       return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        UserToDo existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Does Not exist with the given ID :"+ id));
        userRepo.deleteById(id);
    }

    @Override
    public void deleteAll(UserDto userDto) {
        userRepo.deleteAll();
    }

}
