package com.paysys.participant.application.service;

import com.paysys.participant.domain.Participant;
import com.paysys.participant.application.port.out.ParticipantRepositoryPort;
import com.paysys.participant.application.port.in.LogInUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LogInService implements LogInUseCase {

    @Autowired
    private ParticipantRepositoryPort participantRepositoryPort;

    @Override
    public Boolean processLogOn(String email, String password){
        final Participant foundParticipant = participantRepositoryPort.findByEmail(email);

        return Objects.nonNull(foundParticipant)
                ?foundParticipant.getPassword().equals(password)
                :Boolean.FALSE;

    }
}
