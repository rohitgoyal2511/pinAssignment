package com.assignment.DecomoDigitial.controller;

import com.assignment.DecomoDigitial.common.ApiResponse;
import com.assignment.DecomoDigitial.dto.pin.request.SendPinDTO;
import com.assignment.DecomoDigitial.dto.pin.request.VerifyPinDTO;
import com.assignment.DecomoDigitial.dto.pin.response.SendPinResponse;
import com.assignment.DecomoDigitial.dto.pin.response.VerifyPinResponse;
import com.assignment.DecomoDigitial.enums.ResponseCodes;
import com.assignment.DecomoDigitial.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/otp")
@CrossOrigin
public class PinController {

    @Autowired
    private PinService pinService;

    @PostMapping(value = "send")
    public ResponseEntity<ApiResponse> sendPIN(@RequestBody @Valid SendPinDTO otpDTO) {

        SendPinResponse otpResponse = pinService.sendPIN(otpDTO);

        return new ResponseEntity<>(ApiResponse.buildResponse(otpResponse, ResponseCodes.PIN_SUCCESSFULLY_SENT), HttpStatus.OK);
    }

    @PostMapping(value = "verify")
    public ResponseEntity<ApiResponse> verifyPIN(@RequestBody @Valid VerifyPinDTO verifyPinDTO) {

        VerifyPinResponse verifyPinResponse = pinService.verifyPIN(verifyPinDTO);

        if (verifyPinResponse.isValid()) {
            return new ResponseEntity<>(ApiResponse.buildResponse(null, ResponseCodes.PIN_VERIFITION_SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ApiResponse.buildResponse(null, ResponseCodes.PIN_VERIFITION_FAIL),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
