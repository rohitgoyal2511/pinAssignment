package com.assignment.DecomoDigitial.service.impl;

import com.assignment.DecomoDigitial.common.Constant;
import com.assignment.DecomoDigitial.dto.pin.request.SendPinDTO;
import com.assignment.DecomoDigitial.dto.pin.request.VerifyPinDTO;
import com.assignment.DecomoDigitial.dto.pin.response.SendPinResponse;
import com.assignment.DecomoDigitial.dto.pin.response.VerifyPinResponse;
import com.assignment.DecomoDigitial.entity.PIN;
import com.assignment.DecomoDigitial.exception.ApplicationException;
import com.assignment.DecomoDigitial.repository.PinRepo;
import com.assignment.DecomoDigitial.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PinServiceImpl implements PinService {

    @Autowired
    private PinRepo pinRepo;

    @Override
    public SendPinResponse sendPIN(SendPinDTO sendPinDTO) {

        SendPinResponse pinResponse = new SendPinResponse();

        if (Optional.ofNullable(sendPinDTO.getMsisdn()).isPresent()) {
            if (!checkEligibility(sendPinDTO.getMsisdn())) {
                throw new ApplicationException(Constant.GENERATE_PIN_EXHAUSTED_MESSAGE);
            }
        }

        String pinCode = generatePin();
        pinResponse.setPinId(savePinDetail(sendPinDTO.getMsisdn(), pinCode));
        pinResponse.setPin(pinCode);
        return pinResponse;
    }

    @Override
    public VerifyPinResponse verifyPIN(@Valid VerifyPinDTO verifyPinDTO) {
        VerifyPinResponse response = new VerifyPinResponse();
        boolean valid = false;

        PIN pin = pinRepo.findByPinIdAndVerified(verifyPinDTO.getPinId(), false)
                .orElseThrow(() -> new ApplicationException("This pinId is not active any more"));

            long sentAt = pin.getSentAt();
            long currentTime = Instant.now().toEpochMilli();
            pin.setModifiedAt(currentTime);
            if (((sentAt + Long.parseLong(Constant.PIN_EXPIRATION)) - currentTime) > 0 && verifyPinDTO.getPin().equals(pin.getPin())) {
                pin.setVerified(true);
                pinRepo.save(pin);
                valid = true;
            } else {
                if (pin.getValidationTry() == Constant.PIN_VALIDATE_LIMIT -1) {
                    // delete if pin validate limit exceed
                    pinRepo.delete(pin);
                } else {
                    pin.setValidationTry(pin.getValidationTry() + 1);
                    pinRepo.save(pin);
                }
            }

        response.setValid(valid);
        return response;
    }

    private String generatePin() {
        long multiplerNum = (long) Math.pow(10, Constant.PIN_LENGTH - 1);
        long randomPIN = (long) (Math.random() * (9 * multiplerNum)) + multiplerNum;
        return String.valueOf(randomPIN);
    }

    private boolean checkEligibility(String msisdn) {

        List<PIN> validPins = getValidOtp(msisdn);

        if (validPins.size() < Constant.INVALID_PIN_LIMIT) {
            return true;
        } else {
            return false;
        }
    }

    private List<PIN> getValidOtp(String msisdn) {
        List<PIN> pins = pinRepo.findByMsisdnAndVerifiedOrderBySentAtDesc(msisdn, false);
        List<PIN> validPins = new ArrayList<>();
        long currentTime = Instant.now().toEpochMilli();
        if (pins != null && !pins.isEmpty()) {
            for (PIN pin: pins) {
                //check if pin has expired
                if (((pin.getSentAt() + Long.parseLong(Constant.PIN_EXPIRATION)) - currentTime) > 0) {
                    validPins.add(pin);
                } else {
                    // delete if pin exceed Expiration limit
                    pinRepo.delete(pin);
                }
            }
        }
        return validPins;
    }

    private String savePinDetail(String msisdn, String pinCode) {
        PIN pin = new PIN();
        long currentTime = Instant.now().toEpochMilli();
        pin.setMsisdn(msisdn);
        pin.setPin(pinCode);
        pin.setSentAt(currentTime);
        pin.setModifiedAt(currentTime);
        String pinId = pinRepo.save(pin).getPinId();
        return pinId;
    }
}
