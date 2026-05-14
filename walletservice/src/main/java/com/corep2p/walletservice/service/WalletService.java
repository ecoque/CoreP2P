package com.corep2p.walletservice.service;

import com.corep2p.walletservice.dto.TransactionDto;
import com.corep2p.walletservice.dto.WalletDto;
import com.corep2p.walletservice.entity.Transaction;
import com.corep2p.walletservice.entity.Wallet;
import com.corep2p.walletservice.mapper.WalletMapper;
import com.corep2p.walletservice.repository.TransactionRepository;
import com.corep2p.walletservice.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository; // Geçmiş için eklendi
    private final WalletMapper walletMapper;

    public WalletDto createWallet(String userId, String currency) {
        Wallet newWallet = Wallet.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .balance(BigDecimal.ZERO)
                .currency(currency)
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return walletMapper.toDto(walletRepository.save(newWallet));
    }

    public WalletDto getWalletById(String walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Cüzdan bulunamadı! ID: " + walletId));
        return walletMapper.toDto(wallet);
    }

    @Transactional // İşlemlerden biri hata verirse veritabanını geri alır (Fintech kuralı!)
    public WalletDto deposit(String walletId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Yüklenen miktar 0'dan büyük olmalıdır!");
        }

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Cüzdan bulunamadı!"));

        wallet.setBalance(wallet.getBalance().add(amount));
        wallet.setUpdatedAt(LocalDateTime.now());
        Wallet updatedWallet = walletRepository.save(wallet);

        // İŞLEM GEÇMİŞİNE KAYIT AT
        saveTransaction(walletId, "DEPOSIT", amount);

        return walletMapper.toDto(updatedWallet);
    }

    @Transactional
    public WalletDto withdraw(String walletId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Çekilen miktar 0'dan büyük olmalıdır!");
        }

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Cüzdan bulunamadı!"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Yetersiz bakiye! Mevcut Bakiye: " + wallet.getBalance());
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        wallet.setUpdatedAt(LocalDateTime.now());
        Wallet updatedWallet = walletRepository.save(wallet);

        // İŞLEM GEÇMİŞİNE KAYIT AT
        saveTransaction(walletId, "WITHDRAW", amount);

        return walletMapper.toDto(updatedWallet);
    }

    // İşlem geçmişini getiren metod
    public List<TransactionDto> getWalletHistory(String walletId) {
        List<Transaction> transactions = transactionRepository.findByWalletIdOrderByTransactionDateDesc(walletId);
        return transactions.stream().map(t -> TransactionDto.builder()
                .transactionType(t.getTransactionType())
                .amount(t.getAmount())
                .transactionDate(t.getTransactionDate())
                .build()).collect(Collectors.toList());
    }

    // Tekrarı önlemek için yardımcı metod
    private void saveTransaction(String walletId, String type, BigDecimal amount) {
        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .walletId(walletId)
                .transactionType(type)
                .amount(amount)
                .transactionDate(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);
    }
}