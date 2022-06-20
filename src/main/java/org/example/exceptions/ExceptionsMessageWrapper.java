package org.example.exceptions;

public class ExceptionsMessageWrapper {
    private String message;

    public ExceptionsMessageWrapper() {
    }

    public ExceptionsMessageWrapper(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
