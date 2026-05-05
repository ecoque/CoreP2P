package com.corep2p.walletservice.mapper;

import com.corep2p.walletservice.dto.WalletDto;
import com.corep2p.walletservice.entity.Wallet;
import org.mapstruct.Mapper;

// componentModel = "spring" diyerek bu class'ı Spring'in Service'lerinde @Autowired ile çağırılabilir hale getiriyoruz.
@Mapper(componentModel = "spring")
public interface WalletMapper {

    // Entity'den DTO'ya dönüştür
    WalletDto toDto(Wallet wallet);

    // DTO'dan Entity'ye dönüştür
    Wallet toEntity(WalletDto walletDto);
}