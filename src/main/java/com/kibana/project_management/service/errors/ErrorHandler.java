package com.kibana.project_management.service.errors;

import com.kibana.project_management.service.errors.exceptions.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    //Cuando envíen mal una solicitud
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity clientError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerArgumentNotValid(MethodArgumentNotValidException e){
        List<DatosErrorValidacion> errors = e.getFieldErrors().stream()
                .map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ValidacionDeIntegridad.class, ValidationException.class})
    public ResponseEntity errorHandlerValidation(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
