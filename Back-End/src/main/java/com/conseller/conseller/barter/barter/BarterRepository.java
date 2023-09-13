package com.conseller.conseller.barter.barter;

import com.conseller.conseller.entity.Barter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface BarterRepository extends JpaRepository<Barter, Long> {

    // 물물교환
    Optional<Barter> findByBarterIdx(Long barterIdx);

    @Query("SELECT b FROM Barter b WHERE b.barterCompletedDate IS NULL AND b.barterHost = ?1")
    List<Barter> findByHostIdx(Long userIdx);

//    @Query("SELECT * FROM BARTER WHERE BARTER.subCategory =:subCategoryIdx")
//    Optional<List<Barter>> findBarterIdx(Long subCategoryIdx, String condition);
//
//    Optional<>

    // 물물교환 주인 물건

        // 물물교환

    // 물물교환 손님 물건
}
