package com.conseller.conseller.utils.jwt;

import com.conseller.conseller.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    boolean existsByAccessToken(String token);
    boolean existsByUser_RefreshToken(String token);
}
