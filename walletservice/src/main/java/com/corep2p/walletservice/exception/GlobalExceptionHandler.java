package com.corep2p.walletservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Tüm Controller'ları dinleyen bir bekçi olduğunu belirtir
public class GlobalExceptionHandler {

    // Tüm RuntimeException'ları (bizim fırlattıklarımızı) yakalar
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "İşlem Başarısız");
        errorResponse.put("message", ex.getMessage()); // Bizim yazdığımız "Yetersiz bakiye!" mesajı buraya gelecek

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Geçersiz parametre hatalarını yakalar
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Geçersiz Girdi");
        errorResponse.put("message", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}