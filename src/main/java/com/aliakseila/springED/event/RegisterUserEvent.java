package com.aliakseila.springED.event;

import com.aliakseila.springED.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RegisterUserEvent extends ApplicationEvent {

    private final User user;

    public RegisterUserEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
