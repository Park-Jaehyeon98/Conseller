package com.conseller.conseller.store;

import com.conseller.conseller.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select s from Store s where s.consumer.userIdx = :consumerIdx")
    List<Store> findStoresByConsumerIdx(long consumerIdx);

    @Query("select s from Store s where s.storeStatus = '거래 중' and current_timestamp - s.notificationCreatedDate >= 14 * 60 * 1000")
    List<Store> findByStoreListConfirm();
}
