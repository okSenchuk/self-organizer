package com.sendev.selforganizer.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "project")
public class ApplicationProperties {
    private Cors cors;

    @Getter
    @Setter
    public static class Cors {
        private Boolean allowCredentials;
        private String uiOrigin;
        private Long maxAge;
    }
}
