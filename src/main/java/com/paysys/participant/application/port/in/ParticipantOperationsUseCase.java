package com.paysys.participant.application.port.in;

import com.paysys.participant.domain.Participant;
import com.paysys.participant.adapter.in.web.dto.ParticipantDTO;

import java.util.List;

public interface ParticipantOperationsUseCase {
    Participant getParticipantByEmail(String email);

    Participant getParticipantById(String id);

    List<Participant> getAllParticipants();

    Participant saveParticipant(ParticipantDTO participantDTO);

    Participant deleteParticipantByEmail(String email);
}
