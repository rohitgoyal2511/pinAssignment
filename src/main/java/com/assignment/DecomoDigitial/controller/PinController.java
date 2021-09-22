package com.assignment.decomodigitial.controller;

import com.assignment.decomodigitial.common.ApiResponse;
import com.assignment.decomodigitial.dto.pin.request.SendPinDTO;
import com.assignment.decomodigitial.dto.pin.request.VerifyPinDTO;
import com.assignment.decomodigitial.dto.pin.response.SendPinResponse;
import com.assignment.decomodigitial.dto.pin.response.VerifyPinResponse;
import com.assignment.decomodigitial.enums.ResponseCodes;
import com.assignment.decomodigitial.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pin")
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
