package com.paysys.participant.adapter.in.web;

import com.paysys.participant.domain.Participant;
import com.paysys.account.adapter.in.web.dto.RequestLogInDTO;
import com.paysys.participant.application.port.in.CredentialsVerifierUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CredentialsVerifierUseCase credentialsVerifierUseCase;

    @PostMapping
    public ResponseEntity verifyCredentials(@RequestBody RequestLogInDTO request) {
        final Participant participant = credentialsVerifierUseCase.verifyCredentials(request.getEmail(), request.getPassword());
        return Objects.nonNull(participant)
                ?ResponseEntity.ok(participant)
                :ResponseEntity.badRequest().body(UNNAUTHORIZED_MSG);
    }
}
