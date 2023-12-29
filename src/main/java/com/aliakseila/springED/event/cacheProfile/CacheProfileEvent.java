package com.aliakseila.springED.event.cacheProfile;

import com.aliakseila.springED.model.dto.ProfileDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CacheProfileEvent extends ApplicationEvent {

    private final String username;
    private final ProfileDto profileDto;
    public CacheProfileEvent(Object source, String username, ProfileDto profileDto) {
        super(source);
        this.username = username;
        this.profileDto = profileDto;
    }
}
