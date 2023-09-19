package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.*;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.user.UserValidator;
import com.conseller.conseller.user.dto.request.*;
import com.conseller.conseller.user.dto.response.InfoValidationRequest;
import com.conseller.conseller.user.dto.response.LoginResponse;
import com.conseller.conseller.user.dto.response.UserInfoResponse;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.Authority;
import com.conseller.conseller.user.enums.UserStatus;
import com.conseller.conseller.utils.JwtToken;
import com.conseller.conseller.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User register(SignUpRequest signUpRequest) {

        userValidator.signUpDtoValidate(signUpRequest);

        User user = User.builder()
                .userId(signUpRequest.getUserId())
                .userPassword(signUpRequest.getUserPassword())
                .userEmail(signUpRequest.getUserEmail())
                .userDeposit(0)
                .userNickname(signUpRequest.getUserNickname())
                .userPhoneNumber(signUpRequest.getUserPhoneNumber())
                .userGender(signUpRequest.getUserGender())
                .userAge(signUpRequest.getUserAge())
                .userName(signUpRequest.getUserName())
                .userAccount(signUpRequest.getUserAccount())
                .userRestrictCount(0)
                .userStatus(UserStatus.ACTIVE.getStatus())
                .userAccountBank(AccountBanks.fromString(signUpRequest.getUserAccountBank()).getBank())
                .build();

        //비밀번호 암호화 및 유저 권한 설정
        user.encryptPassword(new BCryptPasswordEncoder());
        user.getRoles().add(Authority.USER.name());
        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getUserPassword());

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 User에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.createToken(authentication);

        // 유저 정보를 따로 가져오기 위한 호출
        Optional<User> loginedUser = userRepository.findByUserId(loginRequest.getUserId());
        User user = loginedUser.get();

        //refresh token db 저장
        user.setRefreshToken(jwtToken.getRefreshToken());

        if (jwtToken.getAccessToken() != null) {
            loginedUser = userRepository.findByUserId(loginRequest.getUserId());
        }

        // 4. 토큰 정보로 response 생성 후 리턴
        return LoginResponse.builder()
                .userIdx(user.getUserIdx())
                .userNickname(user.getUserNickname())
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .build();
    }

    @Override
    public void updateUserInfo(long userIdx, UserInfoRequest userInfoRequest) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("해당 인덱스에 대한 유저가 존재하지 않습니다."));

        user.setUserPassword(userInfoRequest.getUserPassword());
        user.setUserNickname(userInfoRequest.getUserNickname());
        user.setUserEmail(userInfoRequest.getUserEmail());
        user.setUserAccount(userInfoRequest.getUserAccount());
        user.setUserAccountBank(userInfoRequest.getUserAccountBank());
    }

    @Override
    public UserInfoResponse getUserInfo(long userIdx) {

        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("해당 인덱스에 대한 유저가 존재하지 않습니다."));

        return UserInfoResponse.builder()
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .userPassword(user.getUserPassword())
                .userEmail(user.getUserEmail())
                .userPhoneNumber(user.getUserPhoneNumber())
                .userAccount(user.getUserAccount())
                .userAccountBank(user.getUserAccountBank())
                .build();
    }

    @Override
    public void checkUserPassword(UserCheckPasswordRequest userCheckPasswordRequest) {
        //유저의 idx와 비밀번호를 통해 해당 유저가 존재하는지 확인하는 쿼리를 짜야함.
        if (!userRepository.existsByUserIdxAndUserPassword(userCheckPasswordRequest.getUserIdx(),
                userCheckPasswordRequest.getUserPassword())) {
            throw new RuntimeException("해당 idx와 비밀번호를 가진 유저가 존재하지 않습니다.");
        }
    }

    @Override
    public void uploadUserProfile(UserProfileRequest userProfileRequest) {
        //s3가 만들어지면 구현하기로 함.
    }

    @Override
    public void deposit(long userIdx, int deposit) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("없는 유저 입니다."));
        user.setUserDeposit(deposit);
    }

    @Override
    public List<Store> getUserStores(long userIdx) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("없는 유저 입니다."));
        return user.getStores();
    }

    @Override
    public List<Auction> getUserAuctions(long userIdx) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("없는 유저 입니다."));
        return user.getAuctions();
    }

    @Override
    public List<AuctionBid> getUserAuctionBids(long userIdx) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("없는 유저 입니다."));
        return user.getAuctionBids();
    }

    @Override
    public List<Barter> getUserbarters(long userIdx) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("없는 유저 입니다."));
        return user.getBarters();
    }

    @Override
    public List<BarterRequest> getUserBarterRequests(long userIdx) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("없는 유저 입니다."));
        return user.getBarterRequests();
    }

    @Override
    public void deleteUser(long userIdx) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("없는 유저 입니다."));
        user.setUserDeletedDate(LocalDateTime.now());
    }

    //추후 여유날 때 리프레쉬 토큰 재발급 구현
//    public void reIssue(HttpServletRequest request) {
//        // 1. header에서 refresh token 추출
//        String refreshToken = jwtTokenProvider.resolveToken(request);
//
//        // 2. 토큰의 유효성 검사
//        if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
//            // 3. 저장된 refresh token 찾아오기
//            // 4. 리프레쉬 토큰이 유효하다면 액세스 토큰 발급
//            // 5. 리프레쉬 토큰이 유효하지 않다면 리프레쉬 토큰과 액세스 토큰 모두 발급
//        }
//    }

    @Override
    public InfoValidationRequest checkNickname(String nickname) {
        boolean nicknameExists = userRepository.existsByUserNickname(nickname);

        return InfoValidationRequest.builder()
                .status(nicknameExists ? 0 : 1)
                .message(nicknameExists ? "이미 존재하는 닉네임 입니다." : "사용할 수 있는 닉네임 입니다")
                .build();
    }

    @Override
    public InfoValidationRequest checkId(String id) {
        boolean idExists = userRepository.existsByUserId(id);

        return InfoValidationRequest.builder()
                .status(idExists ? 0 : 1)
                .message(idExists ? "이미 존재하는 아이디 입니다." : "사용할 수 있는 아이디 입니다")
                .build();
    }

    @Override
    public InfoValidationRequest checkEmail(String email) {
        boolean emailExists = userRepository.existsByUserEmail(email);

        return InfoValidationRequest.builder()
                .status(emailExists ? 0 : 1)
                .message(emailExists ? "이미 존재하는 이메일 입니다." : "사용할 수 있는 이메일 입니다")
                .build();
    }

    @Override
    public InfoValidationRequest checkPhoneNumber(String phoneNumber) {
        boolean phoneNumberExists = userRepository.existsByUserPhoneNumber(phoneNumber);

        return InfoValidationRequest.builder()
                .status(phoneNumberExists ? 0 : 1)
                .message(phoneNumberExists ? "이미 존재하는 전화번호 입니다." : "사용할 수 있는 전화번호 입니다")
                .build();
    }


}
