package com.conseller.conseller.user;

import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("올바른_회원정보를_저장한다")
    public void 올바른_회원정보를_저장한다() throws Exception {

        //given
        User user = User.builder()
                .userId("test123123")
                .userPassword("test123456!")
                .userNickname("회원가입테스트용1")
                .userPhoneNumber("01050945330")
                .userAccount("0234691047826307")
                .userAccountBank(AccountBanks.fromString("신한은행"))
                .userEmail("ekclstkfka5@gmail.com")
                .userDeposit(0)
                .userStatus(UserStatus.ACTIVE)
                .userRestrictCount(0)
                .build();

        //when
        User savedUser = userRepository.save(user);

        //then
        assertThat("test123123").isEqualTo(savedUser.getUserId());
        assertThat("test123456!").isEqualTo(savedUser.getUserPassword());
        assertThat("01050945330").isEqualTo(savedUser.getUserPhoneNumber());
        assertThat(AccountBanks.SHINHAN.getBank()).isEqualTo(savedUser.getUserAccountBank().getBank());
    }
}
