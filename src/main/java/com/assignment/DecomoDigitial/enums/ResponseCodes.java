package com.assignment.decomodigitial.enums;

import org.springframework.http.HttpStatus;

public enum ResponseCodes {

    //success
    PIN_SUCCESSFULLY_SENT("src-200", "pin code sent successfully", HttpStatus.OK),
    PIN_VERIFITION_SUCCESS("src-200", "pin successfully verified", HttpStatus.OK),

    PIN_VERIFITION_FAIL("erc-400", "please enter a valid pin", HttpStatus.BAD_REQUEST),
    BINDING_RESULT_EXCEPTION("erc-400", "Validation failed", HttpStatus.BAD_REQUEST),;

    private String statusCode;
    private String message;
    private HttpStatus httpStatus;

    ResponseCodes() {
    }

    ResponseCodes(String statusCode, String message, HttpStatus httpStatus) {
        this.statusCode = statusCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
