package com.paysys.participant.application.service;

import com.paysys.participant.domain.Participant;
import com.paysys.participant.adapter.in.web.dto.ParticipantDTO;
import com.paysys.participant.application.port.out.ParticipantRepositoryPort;
import com.paysys.participant.application.port.in.ParticipantOperationsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService implements ParticipantOperationsUseCase {

    @Autowired
    private ParticipantRepositoryPort participantRepositoryPort;

    @Autowired
    private LogInService logInService;

    @Transactional
    public Participant getParticipantByEmail(String email) {
        return participantRepositoryPort.findByEmail(email);
    }

    @Transactional
    public Participant getParticipantById(String id) {
        Optional<Participant> participant = participantRepositoryPort.findById(UUID.fromString(id).toString());
        if (participant.isPresent()) {
            return participant.get();
        }
        return null;
    }

    @Transactional
    public List<Participant> getAllParticipants() {
        return participantRepositoryPort.findAll();
    }

    @Transactional
    public Participant saveParticipant(ParticipantDTO participantDTO) {
        return participantRepositoryPort.save(Participant.toParticipantFrom(participantDTO));
    }

    @Transactional
    public Participant deleteParticipantByEmail(String email) {
        return participantRepositoryPort.deleteByEmail(email);
    }

}
