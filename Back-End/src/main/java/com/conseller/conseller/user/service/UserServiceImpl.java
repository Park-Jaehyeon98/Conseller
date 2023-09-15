package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.user.UserValidator;
import com.conseller.conseller.user.dto.response.InfoValidationRequest;
import com.conseller.conseller.user.dto.request.SignUpRequest;
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

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    public User register(SignUpRequest signUpRequest) {
        userValidator.signUpDtoValidate(signUpRequest);

        User user = User.builder()
                .userId(signUpRequest.getUserId())
                .userPassword(signUpRequest.getUserPassword())
                .userEmail(signUpRequest.getUserEmail())
                .userDeposit(0)
                .userNickname(signUpRequest.getUserNickname())
                .userPhoneNumber(signUpRequest.getUserPhoneNumber())
                .userGender(signUpRequest.getUserGender())
                .userAge(signUpRequest.getUserAge())
                .userName(signUpRequest.getUserName())
                .userAccount(signUpRequest.getUserAccount())
                .userRestrictCount(0)
                .userStatus(UserStatus.ACTIVE)
                .userAccountBank(AccountBanks.fromString(signUpRequest.getUserAccountBank()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public InfoValidationRequest checkNickname(String nickname) {
        boolean nicknameExists = userRepository.existsByUserNickname(nickname);

        return InfoValidationRequest.builder()
                .status(nicknameExists ? 0 : 1)
                .message(nicknameExists ? "이미 존재하는 닉네임 입니다." : "사용할 수 있는 닉네임 입니다")
                .build();
    }

    @Override
    public InfoValidationRequest checkId(String id) {
        boolean idExists = userRepository.existsByUserId(id);

        return InfoValidationRequest.builder()
                .status(idExists ? 0 : 1)
                .message(idExists ? "이미 존재하는 아이디 입니다." : "사용할 수 있는 아이디 입니다")
                .build();
    }

    @Override
    public InfoValidationRequest checkEmail(String email) {
        boolean emailExists = userRepository.existsByUserEmail(email);

        return InfoValidationRequest.builder()
                .status(emailExists ? 0 : 1)
                .message(emailExists ? "이미 존재하는 이메일 입니다." : "사용할 수 있는 이메일 입니다")
                .build();
    }

    @Override
    public InfoValidationRequest checkPhoneNumber(String phoneNumber) {
        boolean phoneNumberExists = userRepository.existsByUserPhoneNumber(phoneNumber);

        return InfoValidationRequest.builder()
                .status(phoneNumberExists ? 0 : 1)
                .message(phoneNumberExists ? "이미 존재하는 전화번호 입니다." : "사용할 수 있는 전화번호 입니다")
                .build();
    }


}
