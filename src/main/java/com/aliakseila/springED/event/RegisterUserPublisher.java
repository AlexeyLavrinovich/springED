package com.aliakseila.springED.event;

import com.aliakseila.springED.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishUserCreateEvent(User user) {
        RegisterUserEvent registerUserEvent = new RegisterUserEvent(this, user);
        applicationEventPublisher.publishEvent(registerUserEvent);
    }

}
