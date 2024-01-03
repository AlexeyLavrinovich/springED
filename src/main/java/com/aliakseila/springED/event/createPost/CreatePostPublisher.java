package com.aliakseila.springED.event.createPost;

import com.aliakseila.springED.model.entity.DateInfo;
import com.aliakseila.springED.model.entity.Post;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePostPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishCreatePostEvent(Post post, DateInfo dateInfo) {
        eventPublisher.publishEvent(new CreatePostEvent(this, post, dateInfo));
    }

}
