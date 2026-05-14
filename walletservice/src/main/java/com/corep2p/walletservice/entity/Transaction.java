package com.corep2p.walletservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    private String id;
    private String walletId;
    private String transactionType; // DEPOSIT (Yatırma) veya WITHDRAW (Çekme)
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}