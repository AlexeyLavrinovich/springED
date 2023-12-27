package com.aliakseila.springED.controller;

import com.aliakseila.springED.entity.User;
import com.aliakseila.springED.event.RegisterUserPublisher;
import com.aliakseila.springED.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegisterUserPublisher publisher;

    @GetMapping
    public ResponseEntity info(){
        return ResponseEntity.ok("Set your full name and password");
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        userService.createUser(user);
        publisher.publishUserCreateEvent(user);
        return ResponseEntity.ok("Registration successfully finished!");
    }

}
