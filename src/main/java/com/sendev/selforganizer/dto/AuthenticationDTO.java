package com.sendev.selforganizer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationDTO {
    private String role;
    private Boolean isAuthenticated;
    private String firstName;
    private String lastName;
}
