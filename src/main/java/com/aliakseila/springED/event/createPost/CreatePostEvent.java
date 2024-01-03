package com.aliakseila.springED.event.createPost;

import com.aliakseila.springED.model.entity.Post;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreatePostEvent extends ApplicationEvent {

    private final Post post;

    public CreatePostEvent(Object source, Post post) {
        super(source);
        this.post = post;
    }
}
