package com.conseller.conseller.gifticon.repository;

import com.conseller.conseller.entity.Gifticon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GifticonRepository extends JpaRepository<Gifticon, Long> {
    Optional<Gifticon> findByGifticonIdx(long gifticonIdx);
}
