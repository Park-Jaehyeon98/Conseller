package com.conseller.conseller.barter.barter;

import com.conseller.conseller.entity.Barter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface BarterRepository extends JpaRepository<Barter, Long> {

    // 물물교환
    Optional<Barter> findByBarterIdx(Long barterIdx);

    @Query("SELECT b FROM Barter b WHERE b.barterCompletedDate IS NULL AND b.barterHost.userIdx = ?1")
    List<Barter> findByHostIdx(Long userIdx);

    @Query("SELECT b FROM Barter b WHERE b.barterCompletedDate IS NULL AND b.barterEndDate <= current_timestamp ")
    List<Barter> findBarterAllExpired();

    @Query("select b from Barter b where b.barterStatus = '제안' or b.barterStatus = '교환 가능'")
    List<Barter> findOngoingBarterList();

    @Query("SELECT b FROM Barter b ORDER BY b.barterCreatedDate ASC")
    List<Barter> findAll();

}
