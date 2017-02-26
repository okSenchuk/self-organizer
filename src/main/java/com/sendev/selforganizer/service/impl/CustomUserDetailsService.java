package com.sendev.selforganizer.service.impl;

import com.sendev.selforganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return Optional.ofNullable(repository.findOne(username))
                .map(user -> new User(user.getEmail(), user.getPassword(), true, true,
                        true, true, AuthorityUtils.createAuthorityList(user.getRole()
                        .name())))
                .orElseThrow(() -> new UsernameNotFoundException(format("User %s not found", username)));
    }

}
