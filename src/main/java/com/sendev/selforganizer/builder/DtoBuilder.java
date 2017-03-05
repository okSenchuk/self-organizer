package com.sendev.selforganizer.builder;

import com.sendev.selforganizer.dto.AuthenticationDTO;
import com.sendev.selforganizer.model.User;
import org.springframework.stereotype.Component;

@Component
public class DtoBuilder {

    public AuthenticationDTO buildAuthDto() {
        return AuthenticationDTO.builder()
                .isAuthenticated(false)
                .build();
    }

    public AuthenticationDTO buildAuthDto(User user, String role) {
        return AuthenticationDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(role)
                .isAuthenticated(true)
                .build();
    }
}
