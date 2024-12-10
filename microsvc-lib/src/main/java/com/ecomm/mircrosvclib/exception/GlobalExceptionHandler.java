package com.ecomm.mircrosvclib.exception;

import com.ecomm.mircrosvclib.models.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Optional<FieldError> firstError = ex.getBindingResult().getFieldErrors().stream().findFirst();

        String errorMessage = firstError.map(FieldError::getDefaultMessage)
                .orElse("Validation failed");

        BaseResponse response = BaseResponse.getClientErrorResponse(errorMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
