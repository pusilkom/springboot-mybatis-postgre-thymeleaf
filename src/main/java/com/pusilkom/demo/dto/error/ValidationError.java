package com.pusilkom.demo.dto.error;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by fahri on 12/16/16.
 */
public class ValidationError {
    private List<FieldError> errors = Lists.newArrayList();

    public ValidationError() {
    }

    public void addFieldError(String path, String message) {
        FieldError error = new FieldError(path, message);
        errors.add(error);
    }

    public List<FieldError> getErrors() {
        return errors;
    }
}
