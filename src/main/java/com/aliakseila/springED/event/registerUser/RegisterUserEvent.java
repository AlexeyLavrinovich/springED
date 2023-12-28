package com.aliakseila.springED.event.registerUser;

import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.model.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RegisterUserEvent extends ApplicationEvent {

    private final User user;
    private final Profile profile;

    public RegisterUserEvent(Object source, User user, Profile profile) {
        super(source);
        this.user = user;
        this.profile = profile;
    }
}
