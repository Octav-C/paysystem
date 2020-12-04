package com.paysys.participant.application.port.in;

import com.paysys.participant.domain.Participant;

public interface CredentialsVerifierUseCase {
    Participant verifyCredentials(String email, String password);
}
