package com.sendev.selforganizer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
public class ApplicationProperties {
    private String uiOrigin;

    public String getUiOrigin() {
        return uiOrigin;
    }

    public void setUiOrigin(String uiOrigin) {
        this.uiOrigin = uiOrigin;
    }
}
