package dev.edugovi.dir.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("dev.edugovi.dir")
public class MainIdProperty {

    private String mainIdentifier;

    public String getMainIdentifier() {
        return this.mainIdentifier;
    }

    public void setMainIdentifier(String mainIdentifier) {
        this.mainIdentifier = mainIdentifier;
    }
}
