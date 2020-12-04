package com.paysys.participant.adapter.in.web;

import com.paysys.participant.domain.Participant;
import com.paysys.participant.adapter.in.web.dto.ParticipantDTO;
import com.paysys.participant.application.port.in.ParticipantOperationsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("payment/system/participant")
public class RegisterController {

    @Autowired
    private ParticipantOperationsUseCase participantOperationsUseCase;

    @GetMapping("/all")
    public ResponseEntity getAllParticipant(){
        return ResponseEntity.ok(participantOperationsUseCase.getAllParticipants());
    }

    @GetMapping
    public Participant getParticipantById(@RequestParam String id){
        return participantOperationsUseCase.getParticipantById(id);
    }

    @GetMapping("/email")
    public Participant getParticipantByEmail(@RequestParam String email){
        return participantOperationsUseCase.getParticipantByEmail(email);
    }

    @PostMapping
    public ResponseEntity registerParticipant(@RequestBody ParticipantDTO participantDTO){
        final Participant participantToBeSaved = participantOperationsUseCase.saveParticipant(participantDTO);

        return Objects.nonNull(participantToBeSaved)
                ?ResponseEntity.ok("Saved")
                :ResponseEntity.badRequest().body("Bad Request");
    }

    @DeleteMapping
    public ResponseEntity deleteParticipantByEmail(@RequestParam String email){
        final Participant participantToBeDeleted = participantOperationsUseCase.deleteParticipantByEmail(email);

        return Objects.nonNull(participantToBeDeleted)
                ?ResponseEntity.ok("Saved")
                :ResponseEntity.badRequest().body("Bad Request");
    }
}
