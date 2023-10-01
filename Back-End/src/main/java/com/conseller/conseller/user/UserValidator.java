package com.conseller.conseller.user;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
import com.conseller.conseller.user.dto.request.LoginRequest;
import com.conseller.conseller.user.dto.request.SignUpRequest;
import com.conseller.conseller.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void signUpDtoValidate(final SignUpRequest signUpRequest) {
        boolean idExists = userRepository.existsByUserId(signUpRequest.getUserId());
        boolean nicknameExists = userRepository.existsByUserNickname(signUpRequest.getUserNickname());
        boolean emailExists = userRepository.existsByUserEmail(signUpRequest.getUserEmail());
        boolean phoneNumber = userRepository.existsByUserPhoneNumber(signUpRequest.getUserPhoneNumber());

        if (idExists) {
            throw new RuntimeException("이미 존재하는 id 입니다.");
        }

        if (nicknameExists) {
            throw new RuntimeException("이미 존재하는 닉네임 입니다.");
        }

        if (emailExists) {
            throw new RuntimeException("이미 존재하는 이메일 입니다.");
        }

        if (phoneNumber) {
            throw new RuntimeException("이미 존재하는 전화번호 입니다.");
        }
    }

    public boolean isVaildByUserIdx(long userIdx) {
        return userRepository.existsByUserIdx(userIdx);
    }

    public User validateLogin(LoginRequest request) {
        // 입력 id 정보가 유효한지 확인
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.WRONG_ID));

        // 입력한 password 정보가 유효한지 확인
        if (!user.checkPassword(new BCryptPasswordEncoder(), request.getUserPassword())) {
            throw new CustomException(CustomExceptionStatus.WRONG_PW);
        }

        // 입력한 유저가 탈퇴한 유저인지 확인
        if (user.getUserDeletedDate() != null) {
            throw new CustomException(CustomExceptionStatus.RESTRICT);
        }

        //입력한 유저가 사용 제한된 유저인지 확인
        if (UserStatus.RESTRICTED.getStatus().equals(user.getUserStatus())) {
            throw new CustomException(CustomExceptionStatus.RESTRICT);
        }

        return user;
    }
}
