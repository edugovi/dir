package dev.edugovi.dir.controller.exception;

public class IdNumberFormatException extends Exception {

    private String idStr;

    private IdNumberFormatException(String idStr) {
        this.idStr = idStr;
    }

    public static IdNumberFormatException getInstance(String idStr) {
        return new IdNumberFormatException(idStr);
    }

    @Override
    public String getMessage() {
        return "Provided ID: " + idStr + ", has a bad format.";
    }
}
