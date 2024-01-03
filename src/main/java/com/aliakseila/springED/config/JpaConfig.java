package com.aliakseila.springED.config;

import com.aliakseila.springED.model.entity.AuditorAwareImpl;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.service.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@AllArgsConstructor
class JpaConfig {

    private final UserRepo userRepo;

    @Bean
    public AuditorAware<Profile> auditorAware() {
        return new AuditorAwareImpl(userRepo);
    }
}
