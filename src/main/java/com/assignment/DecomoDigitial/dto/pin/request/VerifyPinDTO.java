package com.assignment.DecomoDigitial.dto.pin.request;

import com.assignment.DecomoDigitial.common.Constant;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class VerifyPinDTO {

    @NotBlank(message = "Please enter PIN received on your phone. If not, try resend")
    @Size(min = Constant.PIN_LENGTH, max = Constant.PIN_LENGTH, message = "PIN code should be 4 digits")
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "Please enter numeric values only")
    private String pin;
    @NotBlank(message = "msisdn is missing")
    private String msisdn;
    @NotBlank(message = "pinId is missing")
    private String pinId;
}
