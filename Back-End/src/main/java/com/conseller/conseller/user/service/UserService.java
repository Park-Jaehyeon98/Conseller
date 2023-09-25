package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.request.LoginRequest;
import com.conseller.conseller.user.dto.response.InfoValidationRequest;
import com.conseller.conseller.user.dto.request.SignUpRequest;
import com.conseller.conseller.user.dto.response.LoginResponse;

public interface UserService {

    public User register(SignUpRequest signUpRequest);
    public InfoValidationRequest checkNickname(String nickname);
    public InfoValidationRequest checkId(String id);
    public InfoValidationRequest checkEmail(String email);
    public InfoValidationRequest checkPhoneNumber(String phoneNumber);

    public LoginResponse login(LoginRequest loginRequest);
}
