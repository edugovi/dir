package dev.edugovi.dir.controller.exception;

public class IdNotFoundException extends Exception {

    private String idStr;

    private IdNotFoundException(String idStr) {
        this.idStr = idStr;
    }

    public static IdNotFoundException getInstance(String idStr) {
        return new IdNotFoundException(idStr);
    }

    @Override
    public String getMessage() {
        return "Provided ID: " + idStr + ", not found.";
    }
}
