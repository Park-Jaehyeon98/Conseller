package com.conseller.conseller.user;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.SignUpDto;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import com.conseller.conseller.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void 올바른_값의_회원가입을_진행한다() {

        SignUpDto signUpDto = SignUpDto.builder()
                    .userId("test1234")
                    .userPassword("test123456!")
                    .userEmail("test1234@gmail.com")
                    .userAccount("28218930100882")
                    .userNickname("테스트123")
                    .userAccountBank("신한은행")
                    .userPhoneNumber("01050945330")
                    .build();

//        User user = User.builder()
//                .userId(signUpDto.getUserId())
//                .userPassword(signUpDto.getUserPassword())
//                .userEmail(signUpDto.getUserEmail())
//                .userDeposit(0)
//                .userNickname(signUpDto.getUserNickname())
//                .userPhoneNumber(signUpDto.getUserPhoneNumber())
//                .userAccount(signUpDto.getUserAccount())
//                .userRestrictCount(0)
//                .userStatus(UserStatus.ACTIVE)
//                .userAccountBank(AccountBanks.fromString(signUpDto.getUserAccountBank()))
//                .build();


        User savedUser = userService.register(signUpDto);


    }
}
