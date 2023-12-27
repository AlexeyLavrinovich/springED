package com.aliakseila.springED.event;

import com.aliakseila.springED.entity.Profile;
import com.aliakseila.springED.entity.User;
import com.aliakseila.springED.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserListener {

    @Autowired
    private ProfileRepo profileRepo;

    @EventListener
    public void onUserCreateEvent(RegisterUserEvent event){
        User user = event.getUser();
        Profile profile = new Profile(user);
        profileRepo.save(profile);
    }

}
