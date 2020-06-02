package com.appsdev.PaymentSystem.service;

import com.appsdev.PaymentSystem.domain.Participant;
import com.appsdev.PaymentSystem.dto.ParticipantDTO;
import com.appsdev.PaymentSystem.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private LogOnService logOnService;

    @Transactional
    public Participant getParticipantByEmail(String email) {
        return participantRepository.findByEmail(email);
    }

    @Transactional
    public Participant getParticipantById(String id) {
        Optional<Participant> participant = participantRepository.findById(UUID.fromString(id).toString());
        if (participant.isPresent()) {
            return participant.get();
        }
        return null;
    }

    @Transactional
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Transactional
    public Participant saveParticipant(ParticipantDTO participantDTO) {
        return participantRepository.save(Participant.toParticipantFrom(participantDTO));
    }

    @Transactional
    public Participant deleteParticipantByEmail(String email) {
        return participantRepository.deleteByEmail(email);
    }

    @Transactional
    public Participant verifyCredentials(String email, String password) {
        Boolean verifier = logOnService.processLogOn(email, password);

        if (verifier.booleanValue()) {
            return getParticipantByEmail(email);
        }
        return null;
    }
}
