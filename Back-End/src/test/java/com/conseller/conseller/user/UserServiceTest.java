package com.conseller.conseller.user;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.request.SignUpDto;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import com.conseller.conseller.user.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("올바른 정보의 회원정보로 회원 정보를 저장한다.")
    public void regist() {

        //given
        SignUpDto signUpDto = SignUpDto.builder()
                .userId("test1234")
                .userPassword("test123456!")
                .userEmail("test1234@gmail.com")
                .userAccount("28218930100882")
                .userAge(20)
                .userGender('M')
                .userName("김현수")
                .userNickname("테스트123")
                .userAccountBank("신한은행")
                .userPhoneNumber("01050945330")
                .build();

        User user = User.builder()
                .userId(signUpDto.getUserId())
                .userPassword(signUpDto.getUserPassword())
                .userEmail(signUpDto.getUserEmail())
                .userDeposit(0)
                .userNickname(signUpDto.getUserNickname())
                .userPhoneNumber(signUpDto.getUserPhoneNumber())
                .userName(signUpDto.getUserName())
                .userAge(signUpDto.getUserAge())
                .userGender(signUpDto.getUserGender())
                .userAccount(signUpDto.getUserAccount())
                .userRestrictCount(0)
                .userStatus(UserStatus.ACTIVE)
                .userAccountBank(AccountBanks.fromString(signUpDto.getUserAccountBank()))
                .build();

        given(userRepository.save(any(User.class))).willReturn(user);

        //when
        User savedUser = userService.register(signUpDto);

        //then
        assertThat(savedUser.getUserId()).isEqualTo(signUpDto.getUserId());
        assertThat(savedUser.getUserPassword()).isEqualTo(signUpDto.getUserPassword());
    }

    @Test
    @DisplayName("해당 아이디를 가진 유저가 있으면 true를 반환한다.")
    void checkUserId() {
        // given
        SignUpDto signUpDto = SignUpDto.builder()
                .userId("test1234")
                .userPassword("test123456!")
                .userEmail("test1234@gmail.com")
                .userAccount("28218930100882")
                .userAge(20)
                .userGender('M')
                .userName("김현수")
                .userNickname("테스트123")
                .userAccountBank("신한은행")
                .userPhoneNumber("01050945330")
                .build();

        userService.register(signUpDto);
        given(userRepository.existsByUserId(any(String.class))).willReturn(true);

        // when
        boolean result = userRepository.existsByUserId(signUpDto.getUserId());

        // then
        assertThat(result).isTrue();
    }


}
