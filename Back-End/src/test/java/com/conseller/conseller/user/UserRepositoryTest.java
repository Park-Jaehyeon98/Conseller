package com.conseller.conseller.user;

import com.conseller.conseller.TestConfig;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.request.EmailAndNameRequest;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Import({TestConfig.class})
@DataJpaTest
public class UserRepositoryTest {

    private static final String REFRESH_TOKEN = "zzxcv76zxd8vy32";

    @Autowired
    private UserRepository userRepository;

    static private User user;

    @BeforeAll
    static void setupUser() {
        user = User.builder()
                .userId("test123123")
                .userPassword("test123456!")
                .userNickname("회원가입테스트용1")
                .userPhoneNumber("01050945330")
                .userGender('M')
                .userAge(27)
                .userName("테스트")
                .userAccount("0234691047826307")
                .userAccountBank(AccountBanks.fromString("신한은행").getBank())
                .userEmail("test1@gmail.com")
                .userDeposit(0)
                .userStatus(UserStatus.ACTIVE.getStatus())
                .userRestrictCount(0)
                .refreshToken(REFRESH_TOKEN)
                .build();
    }

    @Test
    @DisplayName("올바른 회원정보를 저장한다")
    public void save() throws Exception {

        //given
        userRepository.save(user);

        // when
        Optional<User> savedUser = userRepository.findByUserId(user.getUserId());

        //then
        assertThat(savedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("해당 id의 유저 정보를 가져온다.")
    void findByUserId() {

        // given
        userRepository.save(user);

        //when
        Optional<User> findUser = userRepository.findByUserId(user.getUserId());

        // then
        assertThat(findUser.isPresent()).isTrue();
        assertThat(findUser.get().getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    @DisplayName("없는 id일 경우에 null을 반환한다.")
    void returnNullFindById() {

        // given
        String userId = "test12345";

        // when
        Optional<User> findUser = userRepository.findByUserId(userId);

        // then
        assertThat(findUser.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("이메일과 이름이 일치하는 유저의 정보를 불러올 수 있다.")
    void findUserEmailAndUserName() {

        // given
        userRepository.save(user);

        String userEmail = "test1@gmail.com";
        String userName = "테스트";

        // when
        Optional<User> findUser = userRepository.findByUserEmailAndUserName(userEmail, userName);

        // then
        assertThat(findUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("유저의 id를 통해 refreshToken을 가져온다.")
    void getRefreshToken() {

        // given
        userRepository.save(user);

        // when
        String findRefreshToken = userRepository.findRefreshTokenByUserId("test123123")
                .orElseThrow(() -> new RuntimeException("찾지 못함."));

        // then
        assertThat(findRefreshToken).isEqualTo(REFRESH_TOKEN);
    }
}
