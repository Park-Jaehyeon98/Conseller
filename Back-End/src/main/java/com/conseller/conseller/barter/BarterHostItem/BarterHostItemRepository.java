package com.conseller.conseller.barter.BarterHostItem;

import com.conseller.conseller.entity.BarterHostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarterHostItemRepository extends JpaRepository<BarterHostItem, Long> {
}
