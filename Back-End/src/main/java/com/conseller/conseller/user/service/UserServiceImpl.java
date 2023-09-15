package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.user.UserValidator;
import com.conseller.conseller.user.dto.InfoValidationDto;
import com.conseller.conseller.user.dto.SignUpDto;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    public User register(SignUpDto signUpDto) {
        userValidator.signUpDtoValidate(signUpDto);

        User user = User.builder()
                .userId(signUpDto.getUserId())
                .userPassword(signUpDto.getUserPassword())
                .userEmail(signUpDto.getUserEmail())
                .userDeposit(0)
                .userNickname(signUpDto.getUserNickname())
                .userPhoneNumber(signUpDto.getUserPhoneNumber())
                .userGender(signUpDto.getUserGender())
                .userAge(signUpDto.getUserAge())
                .userName(signUpDto.getUserName())
                .userAccount(signUpDto.getUserAccount())
                .userRestrictCount(0)
                .userStatus(UserStatus.ACTIVE)
                .userAccountBank(AccountBanks.fromString(signUpDto.getUserAccountBank()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public InfoValidationDto checkNickname(String nickname) {
        boolean nicknameExists = userRepository.existsByUserNickname(nickname);

        return InfoValidationDto.builder()
                .status(nicknameExists ? 0 : 1)
                .message(nicknameExists ? "이미 존재하는 닉네임 입니다." : "사용할 수 있는 닉네임 입니다")
                .build();
    }

    @Override
    public InfoValidationDto checkId(String id) {
        boolean idExists = userRepository.existsByUserId(id);

        return InfoValidationDto.builder()
                .status(idExists ? 0 : 1)
                .message(idExists ? "이미 존재하는 아이디 입니다." : "사용할 수 있는 아이디 입니다")
                .build();
    }

    @Override
    public InfoValidationDto checkEmail(String email) {
        boolean emailExists = userRepository.existsByUserEmail(email);

        return InfoValidationDto.builder()
                .status(emailExists ? 0 : 1)
                .message(emailExists ? "이미 존재하는 이메일 입니다." : "사용할 수 있는 이메일 입니다")
                .build();
    }

    @Override
    public InfoValidationDto checkPhoneNumber(String phoneNumber) {
        boolean phoneNumberExists = userRepository.existsByUserPhoneNumber(phoneNumber);

        return InfoValidationDto.builder()
                .status(phoneNumberExists ? 0 : 1)
                .message(phoneNumberExists ? "이미 존재하는 전화번호 입니다." : "사용할 수 있는 전화번호 입니다")
                .build();
    }


}
