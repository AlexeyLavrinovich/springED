package com.aliakseila.springED.event.cacheProfile;

import com.aliakseila.springED.model.dto.ProfileDto;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CacheProfilePublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishCacheProfileEvent(String username, ProfileDto profileDto) {
        eventPublisher.publishEvent(new CacheProfileEvent(this, username, profileDto));
    }

}
