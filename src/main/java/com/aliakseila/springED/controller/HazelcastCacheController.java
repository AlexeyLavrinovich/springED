package com.aliakseila.springED.controller;

import com.hazelcast.core.HazelcastInstance;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
@AllArgsConstructor
public class HazelcastCacheController {

    private final HazelcastInstance hazelcastInstance;

    @GetMapping
    public ResponseEntity getProfileCache(){
        return ResponseEntity.ok(hazelcastInstance.getMap("Profiles"));
    }

}
