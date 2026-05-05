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
@Table(name = "WALLET")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    private String id;

    private String userId;

    private BigDecimal balance;

    private String currency;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}