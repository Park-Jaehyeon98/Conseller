package com.conseller.conseller.user;

import com.conseller.conseller.auction.auction.dto.response.AuctionItemData;
import com.conseller.conseller.auction.bid.dto.response.AuctionBidResponse;
import com.conseller.conseller.barter.barter.barterDto.response.MyBarterResponseDto;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.MyBarterRequestResponseDto;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.store.dto.response.StoreItemData;
import com.conseller.conseller.user.dto.request.*;
import com.conseller.conseller.user.dto.response.*;
import com.conseller.conseller.user.service.UserService;
import com.conseller.conseller.utils.EmailService;
import com.conseller.conseller.utils.dto.CommonResponse;
import com.conseller.conseller.utils.dto.EmailResponse;
import com.conseller.conseller.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    @PostMapping
    public ResponseEntity<UserIdxResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {

        log.info("유저 회원가입 호출");

        long userIdx = userService.register(signUpRequest).getUserIdx();
        
        return ResponseEntity.ok()
                .body(UserIdxResponse.builder().userIdx(userIdx).build());
    }

    //일반 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = userService.login(loginRequest);

        log.info("user nickname : " + loginResponse.getUserNickname());
        log.info("user accessToken : " + loginResponse.getAccessToken());
        log.info("user refreshToken : " + loginResponse.getRefreshToken());

        return ResponseEntity.ok()
                .body(loginResponse);
    }

    //액세스 토큰 재발급 요청
    @GetMapping("/refresh/{userIdx}")
    public ResponseEntity<Object> reCreateAccessToken(HttpServletRequest request,@PathVariable long userIdx) {

        log.info("액세스 토큰 재발급 요청");

        return ResponseEntity.ok()
                .body(userService.reCreateAccessToken(request, userIdx));
    }

    //닉네임 중복체크
    @PostMapping("/nickname")
    public ResponseEntity<Object> checkNickname(@RequestBody NicknameRequest nicknameRequest) {

        InfoValidationRequest infoValidationRequest = userService.checkNickname(nicknameRequest.getUserNickname());

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    //id 중복체크
    @PostMapping("/id")
    public ResponseEntity<Object> checkId(@RequestBody IdRequest idRequest) {

        InfoValidationRequest infoValidationRequest = userService.checkId(idRequest.getUserId());
        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    //이메일 중복체크
    @PostMapping("/email")
    public ResponseEntity<Object> checkEmail(@RequestBody EmailRequest emailRequest) {

        InfoValidationRequest infoValidationRequest = userService.checkEmail(emailRequest.getUserEmail());

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    //전화번호 중복체크
    @PostMapping("/phone-number")
    public ResponseEntity<Object> checkPhoneNumber(@RequestBody PhoneNumberRequest phoneNumberRequest) {

        InfoValidationRequest infoValidationRequest = userService.checkPhoneNumber(phoneNumberRequest.getUserPhoneNumber());

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    //부분 암호화된 아이디 출력
    @PostMapping("/encode/id")
    public ResponseEntity<Object> getEncodeUserId(@RequestBody EmailAndNameRequest emailAndNameRequest) {
        return ResponseEntity.ok()
                .body(userService.getHiddenUserId(emailAndNameRequest));
    }

    // 임시 비밀번호 발급
    @PatchMapping("/encode/pw")
    public ResponseEntity<Object> changeTempPassword(@RequestBody EmailAndIdRequest emailAndIdRequest) throws Exception {

        String tempPassword = userService.generateTemporaryPassword(emailAndIdRequest).getTemporaryPassword();

        EmailResponse emailResponse = EmailResponse.builder()
                .userEmail(emailAndIdRequest.getUserEmail())
                .userPassword(tempPassword)
                .userName(emailAndIdRequest.getUserId())
                .build();

       emailService.sendEmail(emailResponse);

        return ResponseEntity.ok()
                .build();
    }

    //유저 정보 변경
    @PutMapping("/{userIdx}")
    public ResponseEntity<Void> updateUserInfo(@PathVariable long userIdx, @Valid @RequestBody UserInfoRequest userInfoRequest) {

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

    //보증금 넣기
    @PatchMapping("/{userIdx}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable long userIdx, @RequestBody Long deposit) {

        userService.deposit(userIdx, deposit);

        return ResponseEntity.ok().build();
    }

    //내 기프티콘 보기
    @GetMapping("{userIdx}/gifticons")
    public ResponseEntity<Item<List<GifticonResponse>>> getUserGifticons(@PathVariable long userIdx) {

        Item<List<GifticonResponse>> response = new Item<>(userService.getGifticons(userIdx));

        return ResponseEntity.ok()
                .body(response);
    }

    //내 판매 보기
    @GetMapping("/{userIdx}/store")
    public ResponseEntity<Item<List<StoreItemData>>> getUserStores(@PathVariable long userIdx) {

        Item<List<StoreItemData>> response = new Item<>(userService.getUserStores(userIdx));

        return ResponseEntity.ok()
                .body(response);
    }

    //내 구매 보기
    @GetMapping("/{userIdx}/store/purchase")
    public ResponseEntity<Item<List<StoreItemData>>> getUserPurchaseStores(@PathVariable long userIdx) {
        Item<List<StoreItemData>> response = new Item<>(userService.getUserPurchaseStores(userIdx));
        return ResponseEntity.ok()
                .body(response);
    }

    //내 경매 보기
    @GetMapping("/{userIdx}/auction")
    public ResponseEntity<Item<List<AuctionItemData>>> getUserAuctions(@PathVariable long userIdx) {

        Item<List<AuctionItemData>> response = new Item<>(userService.getUserAuctions(userIdx));

        return ResponseEntity.ok()
                .body(response);
    }

    //내 입찰 보기
    @GetMapping("/{userIdx}/auction-bid")
    public ResponseEntity<Item<List<AuctionBidResponse>>> getUserAuctionBids(@PathVariable long userIdx) {

        Item<List<AuctionBidResponse>> response = new Item<>(userService.getUserAuctionBids(userIdx));

        return ResponseEntity.ok()
                .body(response);
    }

    //내 물물교환 보기
    @GetMapping("/{userIdx}/barter")
    public ResponseEntity<Item<List<MyBarterResponseDto>>> getUserBarters(@PathVariable long userIdx) {

        Item<List<MyBarterResponseDto>> response = new Item<>(userService.getUserBarters(userIdx));

        return ResponseEntity.ok()
                .body(response);
    }

    //내 물물교환 요청 보기
    @GetMapping("/{userIdx}/barter-request")
    public ResponseEntity<Item<List<MyBarterRequestResponseDto>>> getUserBarterRequests(@PathVariable long userIdx) {

        Item<List<MyBarterRequestResponseDto>> response = new Item<>(userService.getUserBarterRequests(userIdx));

        return ResponseEntity.ok()
                .body(response);
    }

    //회원 탈퇴
    @DeleteMapping("/{userIdx}")
    public ResponseEntity<Object> deleteUser(@PathVariable long userIdx, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        userService.deleteUser(userIdx, token);
        return ResponseEntity.ok().build();
    }

    //fcm 토큰 발급
    @PostMapping("/firebaseToken/{userIdx}")
    public ResponseEntity<Object> getFirebaseToken(@PathVariable Long userIdx, @RequestBody FirebaseRequest request) {
        userService.getFirebaseToken(userIdx, request);

        return ResponseEntity.ok()
                .build();
    }

    // pattern 저장
    @PostMapping("/savepattern")
    public ResponseEntity<Void> patternSave(@Valid @RequestBody UserPatternRequest userPatternRequest) {
        log.info("유저 패턴 저장");
        userService.patternRegister(userPatternRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/verifypattern")
    public ResponseEntity<LoginResponse> patternLogin(@Valid @RequestBody UserPatternRequest userPatternRequest){
        log.info("유저 패턴 로그인");

        LoginResponse loginResponse = userService.loginPattern(userPatternRequest);

        log.info("user nickname : " + loginResponse.getUserNickname());
        log.info("user accessToken : " + loginResponse.getAccessToken());
        log.info("user refreshToken : " + loginResponse.getRefreshToken());
        userService.patternRegister((userPatternRequest));

        return ResponseEntity.ok()
                .body(loginResponse);

    }

    @PostMapping("/getgifticons")
    public ResponseEntity<Object> getUserGifticonPages(@RequestBody GifticonRequestDTO gifticonRequestDTO) {
        return ResponseEntity.ok()
                .body(userService.getGifticonPage(gifticonRequestDTO));
    }
}
