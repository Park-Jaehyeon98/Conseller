package com.conseller.conseller.user;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.request.EmailAndNameRequest;
import com.conseller.conseller.user.dto.request.SignUpRequest;
import com.conseller.conseller.user.dto.response.InfoValidationRequest;
import com.conseller.conseller.user.dto.response.PartialHiddenUserIdResponse;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import com.conseller.conseller.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

    private static User user;
    private static SignUpRequest signUpRequest;

    @BeforeAll
    static void setupAll() {
        signUpRequest = SignUpRequest.builder()
                .userId("test1234")
                .userPassword("test123456!")
                .userEmail("test1234@gmail.com")
                .userAccount("28218930100882")
                .userNickname("테스트123")
                .userGender('M')
                .userAge(27)
                .userName("김현수")
                .userAccountBank("신한은행")
                .userPhoneNumber("01050945330")
                .build();

        user = User.builder()
                .userId(signUpRequest.getUserId())
                .userPassword(signUpRequest.getUserPassword())
                .userEmail(signUpRequest.getUserEmail())
                .userDeposit(0)
                .userNickname(signUpRequest.getUserNickname())
                .userPhoneNumber(signUpRequest.getUserPhoneNumber())
                .userName(signUpRequest.getUserName())
                .userAge(signUpRequest.getUserAge())
                .userGender(signUpRequest.getUserGender())
                .userAccount(signUpRequest.getUserAccount())
                .userRestrictCount(0)
                .userStatus(UserStatus.ACTIVE.getStatus())
                .userAccountBank(AccountBanks.fromString(signUpRequest.getUserAccountBank()).getBank())
                .build();

    }

    @Test
    @DisplayName("올바른 정보의 회원정보로 저장한다.")
    public void regist() {

        //given
        given(userRepository.save(any(User.class))).willReturn(user);

        //when
        User savedUser = userService.register(signUpRequest);

        //then
        assertThat(savedUser.getUserId()).isEqualTo(signUpRequest.getUserId());
        assertThat(savedUser.getUserPassword()).isEqualTo(signUpRequest.getUserPassword());
    }

    @Test
    @DisplayName("이미 존재하는 id를 입력하면 0과 이미 존재한다는 메세지가 나온다.")
    void checkUserId() {
        // given
        given(userRepository.existsByUserId(user.getUserId())).willReturn(true);

        // when
        InfoValidationRequest result = userService.checkId(user.getUserId());

        // then
        assertThat(result.getStatus()).isEqualTo(0);
        assertThat(result.getMessage()).isEqualTo("이미 존재하는 아이디 입니다.");
    }

    @Test
    @DisplayName("존재하지 않는 id를 입력하면 1과 사용할 수 있는 아이디 입니다라는 메세지가 나온다.")
    void checkNotExistsUserId() {
        // given
        given(userRepository.existsByUserId(any(String.class))).willReturn(false);

        // when
        InfoValidationRequest result = userService.checkId("test4444");

        // then
        assertThat(result.getStatus()).isEqualTo(1);
        assertThat(result.getMessage()).isEqualTo("사용할 수 있는 아이디 입니다");
    }

    @Test
    @DisplayName("유저의 아이디의 앞부분을 * 표시로 암호화 하여 리턴한다.")
    void encodePartialUserId() {
        // given
        EmailAndNameRequest emailAndNameRequest = EmailAndNameRequest.builder()
                .userEmail(user.getUserEmail())
                .userName(user.getName())
                .build();

        given(userRepository.findByUserEmailAndUserName(user.getUserEmail(), user.getName()))
                .willReturn(Optional.of(user));

        // when
        PartialHiddenUserIdResponse result = userService.getHiddenUserId(emailAndNameRequest);

        // then
        System.out.println(result.getUserEncodeId());
        assertThat(result.getUserEncodeId()).isEqualTo("****1234");
    }
}
