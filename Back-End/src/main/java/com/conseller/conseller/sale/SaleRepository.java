package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
