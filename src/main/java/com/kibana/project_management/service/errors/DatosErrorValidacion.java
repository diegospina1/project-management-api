package com.kibana.project_management.service.errors;

import org.springframework.validation.FieldError;

public record DatosErrorValidacion(
        String field,
        String message
) {
    public DatosErrorValidacion(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
