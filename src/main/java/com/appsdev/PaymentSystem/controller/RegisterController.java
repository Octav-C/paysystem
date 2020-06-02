package com.appsdev.PaymentSystem.controller;

import com.appsdev.PaymentSystem.domain.Participant;
import com.appsdev.PaymentSystem.dto.ParticipantDTO;
import com.appsdev.PaymentSystem.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("payment/system/participant")
public class RegisterController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/all")
    public ResponseEntity getAllParticipant(){
        return ResponseEntity.ok(participantService.getAllParticipants());
    }

    @GetMapping
    public Participant getParticipantById(@RequestParam String id){
        return participantService.getParticipantById(id);
    }

    @GetMapping("/email")
    public Participant getParticipantByEmail(@RequestParam String email){
        return participantService.getParticipantByEmail(email);
    }

    @PostMapping
    public ResponseEntity registerParticipant(@RequestBody ParticipantDTO participantDTO){
        final Participant participantToBeSaved = participantService.saveParticipant(participantDTO);

        return Objects.nonNull(participantToBeSaved)
                ?ResponseEntity.ok("Saved")
                :ResponseEntity.badRequest().body("Bad Request");
    }

    @DeleteMapping
    public ResponseEntity deleteParticipantByEmail(@RequestParam String email){
        final Participant participantToBeDeleted = participantService.deleteParticipantByEmail(email);

        return Objects.nonNull(participantToBeDeleted)
                ?ResponseEntity.ok("Saved")
                :ResponseEntity.badRequest().body("Bad Request");
    }
}
