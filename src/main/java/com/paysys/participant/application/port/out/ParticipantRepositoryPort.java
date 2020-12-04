package com.paysys.participant.application.port.out;

import com.paysys.participant.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepositoryPort {

    Participant save(Participant participant);

    List<Participant> findAll();

    Optional<Participant> findById(String id);

    Participant findByEmail(String email);

    Participant deleteByEmail(String email);
}
