package com.corep2p.walletservice.repository;

import com.corep2p.walletservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    // Spring Data JPA sihirbazı: Sadece metod isminden cüzdana ait geçmişi tarihe göre sıralayıp getirir!
    List<Transaction> findByWalletIdOrderByTransactionDateDesc(String walletId);
}