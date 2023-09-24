package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.*;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.user.UserValidator;
import com.conseller.conseller.user.dto.request.*;
import com.conseller.conseller.user.dto.response.*;
import com.conseller.conseller.user.enums.AccountBanks;
import com.conseller.conseller.user.enums.Authority;
import com.conseller.conseller.user.enums.UserStatus;
import com.conseller.conseller.utils.jwt.JwtToken;
import com.conseller.conseller.utils.jwt.JwtTokenProvider;
import com.conseller.conseller.utils.TemporaryValueGenerator;
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

       // 1. 입력된 id, password 기반으로 인증 후 인가 관련 인터페이스 생성
        Authentication authentication = getAuthentication(loginRequest.getUserId(), loginRequest.getUserPassword());

        // 2. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.createToken(authentication);

        // 3. 유저 정보를 따로 가져오기 위한 호출
        Optional<User> loginedUser = userRepository.findByUserId(loginRequest.getUserId());
        User user = loginedUser.get();

        //4. refresh token db 저장
        user.setRefreshToken(jwtToken.getRefreshToken());

        if (jwtToken.getAccessToken() != null) {
            loginedUser = userRepository.findByUserId(loginRequest.getUserId());
        }

        // 5. 토큰 정보로 response 생성 후 리턴
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
    public TemporaryPasswordResponse generateTemporaryPassword(EmailAndIdRequest emailAndIdRequest) {

        // 1. 임시 비밀번호 생성
        String tempPassword = TemporaryValueGenerator.generateTemporaryValue();

        // 2. 해당 이메일과 ID를 가진 유저 불러오기
        User user = userRepository.findByUserEmailAndUserId(emailAndIdRequest.getUserEmail(), emailAndIdRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("없는 유저 정보 입니다."));

        // 3. 임시 비밀번호로 저장
        user.setUserPassword(tempPassword);

        return TemporaryPasswordResponse.builder()
                .temporaryPassword(user.getPassword())
                .build();
    }

    @Override
    public PartialHiddenUserIdResponse getHiddenUserId(EmailAndNameRequest emailAndNameRequest) {

        //1. 이메일과 이름을 통해 유저 정보를 불러온다.
        User user = userRepository.findByUserEmailAndUserName(emailAndNameRequest.getUserEmail(), emailAndNameRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("유저 정보를 불러올 수 없습니다."));

        StringBuilder partialEncodeId = new StringBuilder();
        String userId = user.getUserId();
        int length = userId.length();

        partialEncodeId.append("*".repeat(length / 2));
        partialEncodeId.append(userId.substring(length / 2));

        return PartialHiddenUserIdResponse.builder()
                .userEncodeId(partialEncodeId.toString())
                .build();
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
                .userProfileUrl(user.getUserProfileUrl())
                .userPhoneNumber(user.getUserPhoneNumber())
                .userAccount(user.getUserAccount())
                .userAccountBank(user.getUserAccountBank())
                .build();
    }

    @Override
    public void uploadProfile(long userIdx, String profileUrl) {
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("유저 정보가 존재하지 않습니다."));

        user.setUserProfileUrl(profileUrl);
    }

    //비밀번호 암호화 된걸 가져와야함.
    @Override
    public void checkUserPassword(UserCheckPasswordRequest userCheckPasswordRequest) {
        //유저의 idx와 비밀번호를 통해 해당 유저가 존재하는지 확인하는 쿼리를 짜야함.
        User user = userRepository.findByUserIdx(userCheckPasswordRequest.getUserIdx())
                .orElseThrow(() -> new RuntimeException("유저가 없습니다."));

        if (!user.checkPassword(new BCryptPasswordEncoder(), userCheckPasswordRequest.getUserPassword())) {
            throw new RuntimeException("해당 idx와 비밀번호를 가진 유저가 존재하지 않습니다.");
        }
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
    public List<Barter> getUserBarters(long userIdx) {
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

    @Override
    public AccessTokenResponse reCreateAccessToken(HttpServletRequest request, long userIdx) {
        log.info("refresh token service layer");
        // 0.요청이 들어온 유저의 정보를 db에서 가져온다.
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("해당 idx에 해당하는 유저 정보가 없습니다."));

        // 1. authentication 발급
        Authentication authentication = getAuthentication(user.getUserId(), user.getUserPassword());

        // 2. header에서 refresh token 추출
        String refreshToken = jwtTokenProvider.resolveToken(request);

        // 3. 토큰의 유효성 검사
        if (refreshToken != null
                && jwtTokenProvider.validateToken(refreshToken)
                && refreshToken.equals(user.getRefreshToken())) {
            log.info("refresh token is valid.");
            // 3. access 토큰 재발급
            return AccessTokenResponse.builder()
                    .accessToken(jwtTokenProvider.createAccessToken(authentication))
                    .build();
        } else {
            throw new RuntimeException("refresh token이 만료되었습니다.");
        }
    }

    private Authentication getAuthentication(String userId, String userPassword) {
        log.info("user ID : " + userId);
        log.info("user Password : " + userPassword);
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId, userPassword);

        log.info(authenticationManagerBuilder.getObject().toString());
        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 User에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }

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

        log.info(id + "is" + idExists);

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
