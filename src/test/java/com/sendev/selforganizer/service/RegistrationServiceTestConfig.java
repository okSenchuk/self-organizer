package com.sendev.selforganizer.service;

import com.sendev.selforganizer.builder.EntityBuilder;
import com.sendev.selforganizer.service.impl.RegistrationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class RegistrationServiceTestConfig {

    @Bean
    public RegistrationService registrationService() {
        return new RegistrationServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public EntityBuilder userBuilder() {
        return new EntityBuilder();
    }
}
