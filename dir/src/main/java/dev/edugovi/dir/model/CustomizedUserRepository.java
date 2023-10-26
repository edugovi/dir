package dev.edugovi.dir.model;

public interface CustomizedUserRepository {

    User findByIdNumAndPasswordAndEnabled(Long idNum, String password);

    User findByIdStrAndPasswordAndEnabled(String idStr, String password);

    User findByIdAndPasswordAndEnabled(Long id, String password);
}
