package com.aliakseila.springED.controller;

import com.aliakseila.springED.model.dto.UserDto;
import com.aliakseila.springED.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @SneakyThrows//ToDo: add globalExceptionHandler
    @GetMapping
    public ResponseEntity getUserByUsername(@RequestBody UserDto user){
        return ResponseEntity.ok(userService.findByUsername(user.getUsername()));
    }

    @SneakyThrows //ToDo: add globalExceptionHandler
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

}
