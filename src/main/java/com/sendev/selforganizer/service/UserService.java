package com.sendev.selforganizer.service;

import com.sendev.selforganizer.dto.AuthenticationDTO;

public interface UserService {
    AuthenticationDTO getCurrentUserDetails();
}
