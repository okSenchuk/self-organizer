package com.sendev.selforganizer.service.impl;

import com.sendev.selforganizer.builder.DtoBuilder;
import com.sendev.selforganizer.dto.AuthenticationDTO;
import com.sendev.selforganizer.exception.AuthException;
import com.sendev.selforganizer.model.User;
import com.sendev.selforganizer.repository.UserRepository;
import com.sendev.selforganizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private DtoBuilder builder;

    @Override
    public AuthenticationDTO getCurrentUserDetails() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        Boolean isAuthenticated = Optional.ofNullable(auth).map(a -> a.isAuthenticated()
                && !(a instanceof AnonymousAuthenticationToken)).orElse(false);
        if (isAuthenticated) {
            @SuppressWarnings("unchecked")
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth
                    .getAuthorities();
            String role = authorities.iterator().next().getAuthority();
            User currentUser = Optional.ofNullable(repository.findOne(auth.getName()))
                    .orElseThrow(() -> new AuthException("Authenticated user not found"));
            return builder.buildAuthDto(currentUser, role);
        }
        return builder.buildAuthDto();
    }
}
