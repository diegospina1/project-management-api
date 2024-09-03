package com.kibana.project_management.service.errors.exceptions;

public class ValidacionDeIntegridad extends RuntimeException{
    public ValidacionDeIntegridad(String message) {
        super(message);
    }
}
