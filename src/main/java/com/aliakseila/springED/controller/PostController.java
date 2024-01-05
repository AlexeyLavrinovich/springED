package com.aliakseila.springED.controller;

import com.aliakseila.springED.model.entity.Post;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity getPosts(){
        return ResponseEntity.ok(postService.getAll());
    }

    @PostMapping("/new")
    public ResponseEntity addPost(@AuthenticationPrincipal User user, @RequestBody Post post){
        postService.createPost(user.getProfile(), post);
        return ResponseEntity.ok("Post successfully add!");
    }

    @GetMapping("/feed")
    public ResponseEntity feed(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(postService.getFriendsPosts(user.getProfile()));
    }

}
