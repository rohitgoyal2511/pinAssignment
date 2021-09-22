package com.assignment.decomodigitial.dto.pin.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SendPinDTO {

    @Valid
    @NotBlank(message = "msisdn is missing")
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "Please enter numeric values only")
    @Size(min = 10, max = 12, message = "Msisdn should be 10 to 12 digit long")
    private String msisdn;
}
