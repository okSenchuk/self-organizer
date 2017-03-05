package com.sendev.selforganizer.controller;

import com.sendev.selforganizer.dto.AuthenticationDTO;
import com.sendev.selforganizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/isAuthenticated")
    @ResponseBody
    public AuthenticationDTO checkAuthentication() {
        return userService.getCurrentUserDetails();
    }
}
