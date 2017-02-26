package com.sendev.selforganizer.util;

import com.sendev.selforganizer.dto.Registration;
import com.sendev.selforganizer.model.Role;
import com.sendev.selforganizer.model.User;

public class ModelBuilder {

    public static User user() {
        return User.builder()
                .email("user@gmail.com")
                .firstName("Name")
                .lastName("Surname")
                .password("password")
                .role(Role.USER)
                .build();
    }

    public static Registration registration(String email, String repeatPassword) {
        return Registration.builder()
                .email(email)
                .firstName("Name")
                .lastName("Surname")
                .password("password")
                .repeatPassword(repeatPassword)
                .build();
    }

    public static Registration registration(String email) {
        return registration(email, "password");
    }
}
