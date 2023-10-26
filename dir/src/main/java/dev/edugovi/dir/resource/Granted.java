package dev.edugovi.dir.resource;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Granted extends RepresentationModel<Granted> {

    private final boolean granted;
    private final Long userId;

    @JsonCreator
    public Granted(@JsonProperty("granted") boolean granted, @JsonProperty("id") Long userId) {
        this.granted = granted;
        this.userId = userId;
    }

    public boolean getGranted() {
        return this.granted;
    }
    public Long getUserId() { return this.userId; }

}
