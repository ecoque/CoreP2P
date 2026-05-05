package com.corep2p.walletservice.repository;

import com.corep2p.walletservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

    // JpaRepository'den miras aldığımız için save(), findById(), findAll(), delete()
    // gibi standart SQL operasyonlarının hiçbirini manuel yazmamıza gerek kalmadı!

}