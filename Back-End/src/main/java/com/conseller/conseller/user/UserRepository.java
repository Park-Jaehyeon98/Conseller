package com.conseller.conseller.user;

import com.conseller.conseller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String Id);
    Optional<User> findByUserName(String name);
    Optional<User> findByUserIdx(Long userIdx);
    boolean existsByUserId(String userId);
    boolean existsByUserNickname(String userNickname);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserPhoneNumber(String userPhoneNumber);


    boolean existsByUserIdxAndPassword(long userIdx, String userPassword);
}
