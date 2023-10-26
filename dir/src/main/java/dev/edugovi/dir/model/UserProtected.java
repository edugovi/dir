package dev.edugovi.dir.model;

import java.sql.Timestamp;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userProtected", types = {User.class})
public interface UserProtected {

    /*
    Write here User.class getters.

    So, if we do:
    --> GET /users/1?projection=userProtected

    We'll get only the fields listed below.
    See CustomWebSecurityConfigurerAdapter.class and notice that
    ROLE_EXTERNAL users can get only the protected data.
     */
    String getIdStr();
    Long getIdNum();
    Timestamp getTimestamp();
}
