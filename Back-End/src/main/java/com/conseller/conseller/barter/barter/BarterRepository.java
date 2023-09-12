package com.conseller.conseller.barter.barter;

import com.conseller.conseller.entity.Barter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BarterRepository extends JpaRepository<Barter, Long> {

    // 물물교환
    Optional<Barter> findByBarterIdx(Long barterIdx);



//    @Query("SELECT * FROM BARTER WHERE BARTER.subCategory =:subCategoryIdx")
//    Optional<List<Barter>> findBarterIdx(Long subCategoryIdx, String condition);
//
//    Optional<>

    // 물물교환 주인 물건

        // 물물교환

    // 물물교환 손님 물건
}
