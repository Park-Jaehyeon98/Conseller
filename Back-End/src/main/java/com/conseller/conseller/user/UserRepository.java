package com.conseller.conseller.user;

import com.conseller.conseller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
