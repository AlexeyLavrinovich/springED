package com.aliakseila.springED.event.cacheProfile;

import com.hazelcast.core.HazelcastInstance;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CacheProfileListener {

    private final HazelcastInstance hazelcastInstance;

    @EventListener
    public void onCacheProfileEvent(CacheProfileEvent event) {
        hazelcastInstance.getMap("Profiles").put(event.getUsername(), event.getProfileDto());
    }
}
