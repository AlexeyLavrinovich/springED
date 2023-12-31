package com.aliakseila.springED.event.createPost;

import com.aliakseila.springED.model.entity.Post;
import com.aliakseila.springED.service.repository.PostRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePostListener {

    private final PostRepo postRepo;

    @EventListener
    public void onCreatePostEvent(CreatePostEvent event) {
        Post post = event.getPost();
        postRepo.save(post);
    }
}
