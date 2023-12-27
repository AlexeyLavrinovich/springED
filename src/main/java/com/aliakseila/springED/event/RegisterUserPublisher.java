package com.aliakseila.springED.event;

import com.aliakseila.springED.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterUserPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishUserCreateEvent(User user) {
        eventPublisher.publishEvent(new RegisterUserEvent(this, user));
    }

}
