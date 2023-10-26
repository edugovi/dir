package dev.edugovi.dir.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

    /**
     * Provides an auto-generated id. Not nullable.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Provides an alternative text id. Useful when the data source uses
     * a varchar data type as id. Nullable and unique.
     */
    @Column(unique = true)
    private String idStr;

    /**
     * Provides an alternative numerical id. Useful when the data source uses
     * a long int data type as id. Nullable and unique.
     */
    @Column(unique = true)
    private Long idNum;

    /**
     * User's password
     */
    @Convert(converter = PasswordConverter.class)
    private String password;

    /**
     * User's email
     */
    private String email;

    /**
     * User's timestamp field: multiple purposes
     */
    private Timestamp timestamp;

    /**
     * User's status: true or false
     */
    private boolean enabled;

    protected User() {}

    public Long getId() {
        return id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public Long getIdNum() {
        return idNum;
    }

    public void setIdNum(Long idNum) {
        this.idNum = idNum;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", idStr='" + idStr + '\'' +
                ", idNum=" + idNum +
                ", email='" + email + '\'' +
                ", timestamp=" + timestamp +
                ", enabled=" + enabled +
                '}';
    }
}
