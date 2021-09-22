package com.assignment.decomodigitial.service;

import com.assignment.decomodigitial.dto.pin.request.SendPinDTO;
import com.assignment.decomodigitial.dto.pin.request.VerifyPinDTO;
import com.assignment.decomodigitial.dto.pin.response.SendPinResponse;
import com.assignment.decomodigitial.dto.pin.response.VerifyPinResponse;

import javax.validation.Valid;

public interface PinService {

    SendPinResponse sendPIN(@Valid SendPinDTO sendPinDTO);

    VerifyPinResponse verifyPIN(@Valid VerifyPinDTO verifyPinDTO);
}
