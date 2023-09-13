package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.SignUpDto;

public interface UserService {

    public User register(SignUpDto signUpDto);
}
