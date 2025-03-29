package com.prod_ready_features.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        // get security context
        //get Authentication
        //get the principal
        //get the username

        return Optional.of("Anuj Kumar Sharma");
    }
}
