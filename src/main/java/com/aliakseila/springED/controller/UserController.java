package com.aliakseila.springED.controller;

import com.aliakseila.springED.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @SneakyThrows //ToDo: add global exception handler
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

}
