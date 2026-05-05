package com.corep2p.walletservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {
    private String id;
    private String userId;
    private BigDecimal balance;
    private String currency;
    private String status;
    private LocalDateTime createdAt;
    // Dikkat: updatedAt veya hassas olabilecek başka verileri buraya koymayabiliriz.
    // DTO bizim dışarıya ne göstermek istiyorsak odur.
}