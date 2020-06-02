package com.appsdev.PaymentSystem.repository;

import com.appsdev.PaymentSystem.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, String> {

    Participant findByEmail(String email);

    Participant deleteByEmail(String email);
}
