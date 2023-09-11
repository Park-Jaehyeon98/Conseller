package com.conseller.conseller.barter.barter;

import com.conseller.conseller.entity.Barter;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface barterRepository {

    Optional<Barter> findByBarterIdx(Long barterIdx);

//    @Query("SELECT * FROM BARTER WHERE BARTER.subCategory =:subCategoryIdx")
//    Optional<List<Barter>> findBarterIdx(Long subCategoryIdx, String condition);
//
//    Optional<>
}
