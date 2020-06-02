package com.appsdev.PaymentSystem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionHistory {

    @Id
    private String transactionId;
    private String fromEmail;
    private String toEmail;
    private BigDecimal amount;
    private LocalDateTime txnDate;
}
