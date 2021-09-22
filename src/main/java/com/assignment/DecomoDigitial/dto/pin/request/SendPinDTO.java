package com.assignment.DecomoDigitial.dto.pin.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
public class SendPinDTO {

    @Valid
    @NotBlank(message = "msisdn is missing")
    private String msisdn;
}
