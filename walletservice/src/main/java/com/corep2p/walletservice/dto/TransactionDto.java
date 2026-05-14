package com.corep2p.walletservice.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {
    private String transactionType;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}