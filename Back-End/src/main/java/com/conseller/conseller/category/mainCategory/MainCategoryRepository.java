package com.conseller.conseller.category.mainCategory;

import com.conseller.conseller.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer> {
    Optional<MainCategory> findByMainCategoryIdx(long mainCategory);
}
