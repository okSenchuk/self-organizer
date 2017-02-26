package com.sendev.selforganizer.controller;

import com.sendev.selforganizer.dto.Registration;
import com.sendev.selforganizer.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Registration registration(@RequestBody Registration user) {
        return registrationService.register(user);
    }
}
