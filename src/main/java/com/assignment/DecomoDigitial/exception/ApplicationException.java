package com.assignment.decomodigitial.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Data
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    private String code;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, String code) {
        super(message);
        this.code = code;
    }
}