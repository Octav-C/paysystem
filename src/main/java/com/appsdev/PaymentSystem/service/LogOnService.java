package com.appsdev.PaymentSystem.service;

import com.appsdev.PaymentSystem.domain.Participant;
import com.appsdev.PaymentSystem.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LogOnService {

    @Autowired
    private ParticipantRepository participantRepository;

    public Boolean processLogOn(String email, String password){
        final Participant foundParticipant = participantRepository.findByEmail(email);

        return Objects.nonNull(foundParticipant)
                ?foundParticipant.getPassword().equals(password)
                :Boolean.FALSE;

    }
}
