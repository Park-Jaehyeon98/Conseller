package com.conseller.conseller.user;

import com.conseller.conseller.user.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;

    public void signUpDtoValidate(final SignUpDto signUpDto) {
        boolean idExists = userRepository.existsByUserId(signUpDto.getUserId());
        boolean nicknameExists = userRepository.existsByUserNickname(signUpDto.getUserNickname());
        boolean emailExists = userRepository.existsByUserEmail(signUpDto.getUserEmail());
        boolean phoneNumber = userRepository.existsByUserPhoneNumber(signUpDto.getUserPhoneNumber());

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
