package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.response.InfoValidationDto;
import com.conseller.conseller.user.dto.request.SignUpDto;

public interface UserService {

    public User register(SignUpDto signUpDto);
    public InfoValidationDto checkNickname(String nickname);
    public InfoValidationDto checkId(String id);
    public InfoValidationDto checkEmail(String email);
    public InfoValidationDto checkPhoneNumber(String phoneNumber);
}
