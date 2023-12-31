package com.conseller.conseller.user.service;

import com.conseller.conseller.auction.auction.dto.response.AuctionItemData;
import com.conseller.conseller.auction.bid.dto.response.AuctionBidResponse;
import com.conseller.conseller.barter.barter.barterDto.response.MyBarterResponseDto;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.MyBarterRequestResponseDto;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.store.dto.response.StoreItemData;
import com.conseller.conseller.user.dto.request.*;
import com.conseller.conseller.user.dto.response.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

public interface UserService {

    // 회원가입
    public User register(SignUpRequest signUpRequest);

    //닉네임 중복체크
    public InfoValidationRequest checkNickname(String nickname);

    //ID 중복체크
    public InfoValidationRequest checkId(String id);

    //이메일 중복체크
    public InfoValidationRequest checkEmail(String email);

    //전화번호 중복체크
    public InfoValidationRequest checkPhoneNumber(String phoneNumber);

    //로그인
    public LoginResponse login(LoginRequest loginRequest);

    //액세스 토큰 재발급
    public AccessTokenResponse reCreateAccessToken(HttpServletRequest request, long userIdx);

    //회원정보 변경
    public void updateUserInfo(long userIdx, UserInfoRequest userInfoRequest);

    //임시 비밀번호 발급
    public TemporaryPasswordResponse generateTemporaryPassword(EmailAndIdRequest emailAndIdRequest);

    //암호화된 아이디 조회
    public PartialHiddenUserIdResponse getHiddenUserId(EmailAndNameRequest emailAndNameRequest);

    //회원정보 조회
    public UserInfoResponse getUserInfo(long userIdx);

    //회원 프로필 사진 저장
    public void uploadProfile(long userIdx, String profileUrl);

    //비밀번호 확인
    public void checkUserPassword(UserCheckPasswordRequest userCheckPasswordRequest);

    //보증금 입금
//    public void deposit(long userIdx, int deposit);
    void deposit(long userIdx, Long deposit);

    //내 기프티콘 불러오기
    public List<GifticonResponse> getGifticons(long userIdx);

    //내 판매목록 불러오기
    public List<StoreItemData> getUserStores(long userIdx);

    //내가 구매한 기프티콘에 대한 판매글 목록 불러오기
    public List<StoreItemData> getUserPurchaseStores(long userIdx);

    //내 경매목록 불러오기
    public List<AuctionItemData> getUserAuctions(long userIdx);

    //내 입찰내역 불러오기
    public List<AuctionBidResponse> getUserAuctionBids(long userIdx);

    //내 교환목록 불러오기
    public List<MyBarterResponseDto> getUserBarters(long userIdx);

    //내 교환 요청목록 불러오기
    public List<MyBarterRequestResponseDto> getUserBarterRequests(long userIdx);

    //회원탈퇴
    public void deleteUser(long userIdx, String token);

    //fcm 토큰 발급
    public void setFirebaseToken(Long userIdx, FirebaseRequest request);

    //pattern 저장
    public void patternRegister(@Valid UserPatternRequest userPatternRequest);

    //pattern 로그인
    public LoginResponse loginPattern(UserPatternRequest userPatternRequest);

    //지문 로그인
    public LoginResponse loginFinger(long userIdx);

    //기프티콘 페이지 단위로 보내주기
    public GifticonPageResponse getGifticonPage(GifticonRequestDTO gifticonRequestDTO);
}
