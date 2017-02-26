package com.sendev.selforganizer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Registration {
    @JsonProperty("email")
    @NotNull
    private String email;
    @JsonProperty("firstName")
    @NotNull
    private String firstName;
    @JsonProperty("lastName")
    @NotNull
    private String lastName;
    @JsonProperty("password")
    @NotNull
    private String password;
    @JsonProperty("repeatPassword")
    @NotNull
    private String repeatPassword;
}
