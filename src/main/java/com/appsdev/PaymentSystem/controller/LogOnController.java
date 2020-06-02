package com.appsdev.PaymentSystem.controller;

import com.appsdev.PaymentSystem.domain.Participant;
import com.appsdev.PaymentSystem.dto.RequestLogInDTO;
import com.appsdev.PaymentSystem.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("payment/system/log")
public class LogOnController {

    private final static String UNNAUTHORIZED_MSG = "Unnauthorized!";

    @Autowired
    private ParticipantService participantService;
    @PostMapping
    public ResponseEntity verifyCredentials(@RequestBody RequestLogInDTO request) {
        final Participant participant = participantService.verifyCredentials(request.getEmail(), request.getPassword());
        return Objects.nonNull(participant)
                ?ResponseEntity.ok(participant)
                :ResponseEntity.badRequest().body(UNNAUTHORIZED_MSG);
    }
}
