package com.assignment.decomodigitial.common;

import com.assignment.decomodigitial.enums.ResponseCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    static String SUCCESS = "success";
    Status status;
    T result;

    public static <T> ApiResponse buildResponse(T result, String message, String statuscode) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(result);
        Status status = new Status();
        status.setCode(statuscode);
        status.setMessage(StringUtils.isNotEmpty(message) ? message : SUCCESS);
        apiResponse.setStatus(status);
        return apiResponse;
    }

    public static <T> ApiResponse buildResponse(T result, ResponseCodes code) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(result);
        Status status = new Status();
        status.setCode(code.getStatusCode());
        status.setMessage(StringUtils.isNotEmpty(code.getMessage()) ? code.getMessage() : SUCCESS);
        apiResponse.setStatus(status);
        return apiResponse;
    }

    @Data
    public static class Status {
        private String code;
        private String message;
    }
}
