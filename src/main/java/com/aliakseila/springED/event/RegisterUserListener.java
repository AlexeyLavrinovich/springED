package com.aliakseila.springED.event;

import com.aliakseila.springED.service.repository.ProfileRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterUserListener {

    private final ProfileRepo profileRepo;

    @EventListener
    public void onUserCreateEvent(RegisterUserEvent event) {
        event.getProfile().setUser(event.getUser());
        profileRepo.save(event.getProfile());
    }
}
