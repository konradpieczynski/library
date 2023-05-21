package com.library.library.controller;

import com.library.library.domain.User;
import com.library.library.domain.UserDto;
import com.library.library.mapper.UserMapper;
import com.library.library.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    @Autowired
    private final UserDbService service;
    @Autowired
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> Users = service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(Users));
    }
    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(service.getUser(userId)));
    }
    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException{
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        service.saveUser(user);
        return ResponseEntity.ok().build();
    }
}
