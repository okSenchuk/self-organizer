package com.sendev.selforganizer.builder;

import com.sendev.selforganizer.dto.Registration;
import com.sendev.selforganizer.model.Role;
import com.sendev.selforganizer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    @Autowired
    private PasswordEncoder encoder;

    public User build(Registration registration) {
        return User.builder()
                .email(registration.getEmail())
                .firstName(registration.getFirstName())
                .lastName(registration.getLastName())
                .password(encoder.encode(registration.getPassword()))
                .role(Role.USER)
                .build();
    }
}
