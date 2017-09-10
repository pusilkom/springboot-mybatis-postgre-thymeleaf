package com.pusilkom.demo.dto.error;

/**
 * Created by fahri on 12/16/16.
 */
public class FieldError {
    private String field;

    private String message;

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
