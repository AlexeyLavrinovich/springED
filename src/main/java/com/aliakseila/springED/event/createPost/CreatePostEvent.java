package com.aliakseila.springED.event.createPost;

import com.aliakseila.springED.model.entity.DateInfo;
import com.aliakseila.springED.model.entity.Post;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreatePostEvent extends ApplicationEvent {

    private final Post post;
    private final DateInfo dateInfo;

    public CreatePostEvent(Object source, Post post, DateInfo dateInfo) {
        super(source);
        this.post = post;
        this.dateInfo = dateInfo;
    }
}
