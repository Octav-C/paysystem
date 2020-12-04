package com.paysys.participant.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantDTO {

    private String name;
    private String surname;
    private String email;
    @JsonFormat(pattern = "ddMMyyyy")
    private LocalDate dateOfBorn;
    private String password;

}
