package com.corep2p.walletservice.controller;

import java.math.BigDecimal;
import java.util.List;

import com.corep2p.walletservice.dto.TransactionDto;
import com.corep2p.walletservice.dto.WalletDto;
import com.corep2p.walletservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Bu sınıfın bir API uç noktası olduğunu belirtir
@RequestMapping("/api/v1/wallets") // Ana URL yolumuzu belirliyoruz
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    // 1. Yeni Cüzdan Oluşturma Endpoint'i (POST İsteği)
    @PostMapping
    public ResponseEntity<WalletDto> createWallet(@RequestParam String userId, @RequestParam String currency) {
        WalletDto newWallet = walletService.createWallet(userId, currency);
        return ResponseEntity.ok(newWallet); // HTTP 200 OK ile DTO'yu döndürür
    }

    // 2. Cüzdan Bilgisi Getirme Endpoint'i (GET İsteği)
    @GetMapping("/{walletId}")
    public ResponseEntity<WalletDto> getWalletById(@PathVariable String walletId) {
        WalletDto wallet = walletService.getWalletById(walletId);
        return ResponseEntity.ok(wallet);
    }
    // 3. Para Yatırma Endpoint'i
    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<WalletDto> deposit(@PathVariable String walletId, @RequestParam BigDecimal amount) {
        WalletDto updatedWallet = walletService.deposit(walletId, amount);
        return ResponseEntity.ok(updatedWallet);
    }

    // 4. Para Çekme Endpoint'i
    @PostMapping("/{walletId}/withdraw")
    public ResponseEntity<WalletDto> withdraw(@PathVariable String walletId, @RequestParam BigDecimal amount) {
        WalletDto updatedWallet = walletService.withdraw(walletId, amount);
        return ResponseEntity.ok(updatedWallet);
    }
    // 5. Cüzdan Geçmişi (Hesap Özeti) Endpoint'i
    @GetMapping("/{walletId}/history")
    public ResponseEntity<List<TransactionDto>> getWalletHistory(@PathVariable String walletId) {
        return ResponseEntity.ok(walletService.getWalletHistory(walletId));
    }
}