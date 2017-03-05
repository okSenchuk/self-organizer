package com.sendev.selforganizer.service.impl;


import com.sendev.selforganizer.builder.EntityBuilder;
import com.sendev.selforganizer.dto.Registration;
import com.sendev.selforganizer.exception.RegistrationException;
import com.sendev.selforganizer.model.User;
import com.sendev.selforganizer.repository.UserRepository;
import com.sendev.selforganizer.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.lang.String.format;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private EntityBuilder builder;

    @Override
    public Registration register(Registration registration) {
        User user = repository.findOne(registration.getEmail());
        if (user == null) {
            if (!Objects.equals(registration.getPassword(), registration.getRepeatPassword())) {
                throw new RegistrationException(format("Password and repeat password mismatch for user %s.", registration.getEmail()));
            }
            repository.save(builder.buildUser(registration));
            return registration;
        }
        throw new RegistrationException(format("User %s already exists.", registration.getEmail()));
    }
}
