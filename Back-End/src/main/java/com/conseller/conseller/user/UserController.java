package com.conseller.conseller.user;

import com.conseller.conseller.entity.*;
import com.conseller.conseller.user.dto.request.*;
import com.conseller.conseller.user.dto.response.InfoValidationRequest;
import com.conseller.conseller.user.dto.response.LoginResponse;
import com.conseller.conseller.user.dto.response.UserInfoResponse;
import com.conseller.conseller.user.service.UserService;
import com.conseller.conseller.utils.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("유저 회원가입 호출");
        userService.register(signUpRequest);
        
        return ResponseEntity.ok()
                .build();
    }

    //일반 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = userService.login(loginRequest);

        log.info("user nickname : " + loginResponse.getUserNickname());
        log.info("user accessToken : " + loginResponse.getAccessToken());
        log.info("user refreshToken : " + loginResponse.getRefreshToken());

        return ResponseEntity.ok()
                .body(loginResponse);
    }

//    @PostMapping("/refresh")
//    public ResponseEntity<Object> reCreateAccessToken(@RequestBody LoginRequest loginRequest) {
//
//    }

    //닉네임 중복체크
    @GetMapping("/{userNickname}")
    public ResponseEntity<Object> checkNickname(@PathVariable String userNickname) {

        InfoValidationRequest infoValidationRequest = userService.checkNickname(userNickname);

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    //id 중복체크
    @GetMapping("/{userId}")
    public ResponseEntity<Object> checkId(@PathVariable String userId) {
        InfoValidationRequest infoValidationRequest = userService.checkId(userId);

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    //이메일 중복체크
    @GetMapping("/{userEmail}")
    public ResponseEntity<Object> checkEmail(@PathVariable String userEmail) {
        InfoValidationRequest infoValidationRequest = userService.checkEmail(userEmail);

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    //전화번호 중복체크
    @GetMapping("/{userPhoneNumber}")
    public ResponseEntity<Object> checkPhoneNumber(@PathVariable String userPhoneNumber) {
        InfoValidationRequest infoValidationRequest = userService.checkPhoneNumber(userPhoneNumber);

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    //아이디 찾기
    @PostMapping("/id")
    public ResponseEntity<Object> getEncodeUserId(@RequestBody EmailAndNameRequest emailAndNameRequest) {
        return ResponseEntity.ok()
                .body(null);
    }

    //비밀번호 찾기
    @PatchMapping("/pw")
    public ResponseEntity<Object> changeTempPassword(@RequestBody EmailAndIdRequest emailAndIdRequest) {
        return ResponseEntity.ok()
                .body(null);
    }

    //유저 정보 변경
    @PutMapping("/{userIdx}")
    public ResponseEntity<Void> updateUserInfo(@PathVariable long userIdx, @RequestBody UserInfoRequest userInfoRequest) {
        userService.updateUserInfo(userIdx, userInfoRequest);
        return ResponseEntity.ok().build();
    }

    //유저 정보 조회
    @GetMapping("/{userIdx}/userInfo")
    public ResponseEntity<UserInfoResponse> getUserInfoByUserIdx(@PathVariable long userIdx) {
        return ResponseEntity.ok()
                .body(userService.getUserInfo(userIdx));
    }

    //내 정보 변경용 비밀번호 확인
    @PostMapping("/valid")
    public ResponseEntity<CommonResponse> checkUserPassword(@RequestBody UserCheckPasswordRequest userCheckPasswordRequest) {
        userService.checkUserPassword(userCheckPasswordRequest);
        return ResponseEntity.ok().build();
    }

    //프로필 사진 업로드
    @PostMapping("/profile")
    public ResponseEntity<Void> uploadUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        userService.uploadUserProfile(userProfileRequest);
        return ResponseEntity.ok().build();
    }

    //보증금 넣기
    @PatchMapping("/{userIdx}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable long userIdx, @RequestBody int deposit) {
        userService.deposit(userIdx, deposit);
        return ResponseEntity.ok().build();
    }

    //내 판매 보기
    @GetMapping("/{userIdx}/store")
    public ResponseEntity<List<Store>> getUserStores(@PathVariable long userIdx) {
        return ResponseEntity.ok()
                .body(userService.getUserStores(userIdx));
    }

    //내 경매 보기
    @GetMapping("/{userIdx}/auction")
    public ResponseEntity<List<Auction>> getUserAuctions(@PathVariable long userIdx) {
        return ResponseEntity.ok()
                .body(userService.getUserAuctions(userIdx));
    }

    //내 입찰 보기
    @GetMapping("/{userIdx}/auction-bid")
    public ResponseEntity<List<AuctionBid>> getUserAuctionBids(@PathVariable long userIdx) {
        return ResponseEntity.ok()
                .body(userService.getUserAuctionBids(userIdx));
    }

    //내 물물교환 보기
    @GetMapping("/{userIdx}/barer")
    public ResponseEntity<List<Barter>> getUserBarters(@PathVariable long userIdx) {
        return ResponseEntity.ok()
                .body(userService.getUserbarters(userIdx));
    }

    //내 물물교환 요청 보기
    @GetMapping("/{userIdx}/barer-request")
    public ResponseEntity<List<BarterRequest>> getUserBarterRequests(@PathVariable long userIdx) {
        return ResponseEntity.ok()
                .body(userService.getUserBarterRequests(userIdx));
    }

    //회원 탈퇴
    @DeleteMapping("/{userIdx}")
    public ResponseEntity<Object> deleteUser(@PathVariable long userIdx) {
        userService.deleteUser(userIdx);
        return ResponseEntity.ok().build();
    }

}
