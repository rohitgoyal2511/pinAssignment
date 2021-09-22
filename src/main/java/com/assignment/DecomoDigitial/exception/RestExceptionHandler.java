package com.assignment.DecomoDigitial.exception;

import com.assignment.DecomoDigitial.common.ApiResponse;
import com.assignment.DecomoDigitial.enums.ResponseCodes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse> handleApplicationException(ApplicationException ex) {

        String[] error = ex.getMessage().split("\\|\\|");
        if (error.length > 1) {
            return new ResponseEntity<>(ApiResponse.buildResponse(null, error[1], error[0]), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(ApiResponse.buildResponse(null, ex.getMessage(), "erc-400"), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ApiResponse.buildResponse(null, getFieldErrors(ex), ResponseCodes.BINDING_RESULT_EXCEPTION.getStatusCode()),
                HttpStatus.BAD_REQUEST);
    }

    private String getFieldErrors(MethodArgumentNotValidException ex) {
        StringBuilder builder = new StringBuilder();
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            if (builder.length() > 0) {
                builder.append(",");
            }
            builder.append(fieldError.getDefaultMessage());
        }
        return builder.toString();
    }
}
