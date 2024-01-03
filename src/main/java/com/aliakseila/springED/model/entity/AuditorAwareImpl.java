package com.aliakseila.springED.model.entity;

import com.aliakseila.springED.service.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@AllArgsConstructor
public class AuditorAwareImpl implements AuditorAware<Profile> {

    private final UserRepo userRepo;

    @Override
    public Optional<Profile> getCurrentAuditor() {
        return userRepo.findByUsername(
                        ((User) SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal()).getUsername())
                .map(User::getProfile);
    }
}
