package com.paysys.participant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paysys.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.paysys.participant.adapter.in.web.dto.ParticipantDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {
    @Id
    private String participantId;

    private String name;

    private String surname;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Account account;

    @Column(unique = true)
    private String email;

    @JsonFormat(pattern = "ddMMyyyy")
    private LocalDate dateOfBorn;

    @JsonIgnore
    private String password;

    public static Participant toParticipantFrom(ParticipantDTO participantDTO) {
        Account account = Account.builder()
                .accountId(UUID.randomUUID().toString())
                .balance(BigDecimal.ZERO)
                .build();
        return Participant.builder()
                .participantId(UUID.randomUUID().toString())
                .account(account)
                .email(participantDTO.getEmail())
                .dateOfBorn(participantDTO.getDateOfBorn())
                .name(participantDTO.getName())
                .surname(participantDTO.getSurname())
                .password(participantDTO.getPassword())
                .build();

    }
}
