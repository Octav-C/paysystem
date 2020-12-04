package com.paysys.participant.application.service;

import com.paysys.participant.domain.Participant;
import com.paysys.participant.application.port.in.CredentialsVerifierUseCase;
import com.paysys.participant.application.port.in.LogInUseCase;
import com.paysys.participant.application.port.in.ParticipantOperationsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CredentialsVerifierService implements CredentialsVerifierUseCase {

    @Autowired
    private LogInUseCase logInUseCase;

    @Autowired
    private ParticipantOperationsUseCase participantOperationsUseCase;

    @Override
    @Transactional
    public Participant verifyCredentials(String email, String password) {
        Boolean verifier = logInUseCase.processLogOn(email, password);

        if (verifier.booleanValue()) {
            return participantOperationsUseCase.getParticipantByEmail(email);
        }
        return null;
    }
}
