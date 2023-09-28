package com.conseller.conseller.gifticon.repository;

import com.conseller.conseller.entity.UsedGifticon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedGifticonRepository extends JpaRepository<UsedGifticon, Long> {
    boolean existsByUsedGifticonBarcode(String usedGifticonBarcode);
}
