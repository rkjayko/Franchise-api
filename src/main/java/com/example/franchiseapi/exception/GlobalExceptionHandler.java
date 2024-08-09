package com.example.franchiseapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.FranchiseAlreadyExistsException.class)
    public ResponseEntity<String> handleFranchiseAlreadyExists(CustomException.FranchiseAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(CustomException.FranchiseIdNotFoundException.class)
    public ResponseEntity<String> handleFranchiseIdNotFoundException(CustomException.FranchiseIdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CustomException.BranchAlreadyExistsException.class)
    public ResponseEntity<String> handleBranchAlreadyExists(CustomException.BranchAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(CustomException.BranchIdNotFoundException.class)
    public ResponseEntity<String> handleFranchiseIdNotFoundException(CustomException.BranchIdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CustomException.ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExists(CustomException.ProductAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(CustomException.ProductIdNotFoundException.class)
    public ResponseEntity<String> handleProductIdNotFoundException(CustomException.ProductIdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CustomException.BrandBelongToFranchiseException.class)
    public ResponseEntity<String> handleBrandBelongToFranchiseException(CustomException.BrandBelongToFranchiseException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}