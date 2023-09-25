package com.conseller.conseller.user;

import com.conseller.conseller.user.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

}
