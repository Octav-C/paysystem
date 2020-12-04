package com.paysys.account.adapter.in.web;

import com.paysys.account.application.port.in.SendMoneyUseCase;
import com.paysys.account.domain.TransferStatus;
import com.paysys.account.adapter.in.web.dto.TransferDTO;
import com.paysys.account.adapter.in.web.dto.TransferPrivate;
import com.paysys.account.exception.TransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment/system/transfer")
public class TransferController {

    private final static String SUCCESS_MSG = "SUCCESS";

    private final static String ERROR_MSG = "ERROR IN TRANSACTION!";

    @Autowired
    private SendMoneyUseCase sendMoneyUseCase;

    @PostMapping
    public ResponseEntity transferFromAccountToAccount(@RequestBody TransferDTO transferDTO) {
        final TransferStatus transferStatus;
        try {
            transferStatus = sendMoneyUseCase.transferFunds(
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

        final TransferStatus transferStatus = sendMoneyUseCase.transferFunds(
                transferPrivate.getToAccount(),
                transferPrivate.getAmount());

        return transferStatus.equals(TransferStatus.SUCCESS)
                ? ResponseEntity.ok(SUCCESS_MSG)
                : ResponseEntity.badRequest().body(transferStatus);

    }

}
