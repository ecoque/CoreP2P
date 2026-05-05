package com.corep2p.walletservice.service;

import com.corep2p.walletservice.mapper.WalletMapper;
import com.corep2p.walletservice.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j // Loglama yapabilmek için ekledik
public class WalletService {

    // Veritabanı işlemleri için
    private final WalletRepository walletRepository;

    // DTO dönüşümleri için
    private final WalletMapper walletMapper;

    // Burada @Autowired kullanmadık çünkü Lombok'un @RequiredArgsConstructor
    // anotasyonu final olan bu değişkenler için Constructor Injection'ı otomatik yapıyor.
    // (Mülakatlarda "Neden @Autowired yerine Constructor Injection kullanıyorsun?" sorusunun cevabıdır!)

}