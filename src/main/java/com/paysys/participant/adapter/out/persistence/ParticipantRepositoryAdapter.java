package com.paysys.participant.adapter.out.persistence;

import com.paysys.participant.application.port.out.ParticipantRepositoryPort;
import com.paysys.participant.domain.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public class ParticipantRepositoryAdapter implements ParticipantRepositoryPort {

    @Autowired
    private ParticipantJpaRepository participantJpaRepository;

    @Override
    public Participant save(Participant participant) {
        return participantJpaRepository.save(participant);
    }

    @Override
    public List<Participant> findAll() {
        return participantJpaRepository.findAll();
    }

    @Override
    public Optional<Participant> findById(String id) {
        return participantJpaRepository.findById(id);
    }

    @Override
    public Participant findByEmail(String email) {
        return participantJpaRepository.findByEmail(email);
    }

    @Override
    public Participant deleteByEmail(String email) {
        return participantJpaRepository.deleteByEmail(email);
    }

    @Repository
    public interface ParticipantJpaRepository extends JpaRepository<Participant, String> {
        Participant findByEmail(String email);

        Participant deleteByEmail(String email);
    }

}
