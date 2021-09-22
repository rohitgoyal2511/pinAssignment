package com.assignment.DecomoDigitial.service;

import com.assignment.DecomoDigitial.dto.pin.request.SendPinDTO;
import com.assignment.DecomoDigitial.dto.pin.request.VerifyPinDTO;
import com.assignment.DecomoDigitial.dto.pin.response.SendPinResponse;
import com.assignment.DecomoDigitial.dto.pin.response.VerifyPinResponse;

import javax.validation.Valid;

public interface PinService {

    SendPinResponse sendPIN(@Valid SendPinDTO sendPinDTO);

    VerifyPinResponse verifyPIN(@Valid VerifyPinDTO verifyPinDTO);
}
