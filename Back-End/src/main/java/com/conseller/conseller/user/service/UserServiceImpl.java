package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.user.dto.SignUpDto;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User register(SignUpDto signUpDto) {
        User user = User.builder()
                .userId(signUpDto.getUserId())
                .userPassword(signUpDto.getUserPassword())
                .userEmail(signUpDto.getUserEmail())
                .userDeposit(0)
                .userNickname(signUpDto.getUserNickname())
                .userPhoneNumber(signUpDto.getUserPhoneNumber())
                .userAccount(signUpDto.getUserAccount())
                .userRestrictCount(0)
                .userStatus(UserStatus.ACTIVE)
                .userAccountBank(AccountBanks.fromString(signUpDto.getUserAccountBank()))
                .build();

        userRepository.save(user);
        return user;
    }
}
