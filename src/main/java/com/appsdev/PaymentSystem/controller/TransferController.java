package com.appsdev.PaymentSystem.controller;

import com.appsdev.PaymentSystem.domain.TransferStatus;
import com.appsdev.PaymentSystem.dto.TransferDTO;
import com.appsdev.PaymentSystem.dto.TransferPrivate;
import com.appsdev.PaymentSystem.exception.TransferException;
import com.appsdev.PaymentSystem.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("payment/system/transfer")
public class TransferController {

    private final static String SUCCESS_MSG = "SUCCESS";

    private final static String ERROR_MSG = "ERROR IN TRANSACTION!";

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity transferFromAccountToAccount(@RequestBody TransferDTO transferDTO) {
        final TransferStatus transferStatus;
        try {
            transferStatus = transferService.transferFunds(
                    transferDTO.getFromAccount(),
                    transferDTO.getToAccount(),
                    transferDTO.getAmount());
        }
        catch (TransferException transExc) {
            return ResponseEntity.badRequest().body(transExc.getMessage());
        }

        return transferStatus.equals(TransferStatus.SUCCESS)
                ? ResponseEntity.ok(SUCCESS_MSG)
                : ResponseEntity.badRequest().body(transferStatus);
    }

    @PostMapping("/private")
    public ResponseEntity addBalanceToAccount(@RequestBody TransferPrivate transferPrivate) {

        final TransferStatus transferStatus = transferService.transferFunds(
                transferPrivate.getToAccount(),
                transferPrivate.getAmount());

        return transferStatus.equals(TransferStatus.SUCCESS)
                ? ResponseEntity.ok(SUCCESS_MSG)
                : ResponseEntity.badRequest().body(transferStatus);

    }

}
