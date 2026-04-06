package com.users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expense.ModalDto.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleCustomValidationException(ValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 0);
        response.put("message", "Validation Failed");
        response.put("errorField", ex.getField());
        response.put("errorMessage", ex.getCustomMessage());

        return ResponseEntity.ok(response); // Or HttpStatus.BAD_REQUEST if needed
    }

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
