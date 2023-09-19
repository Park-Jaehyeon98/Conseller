package com.conseller.conseller.user.service;

import com.conseller.conseller.entity.*;
import com.conseller.conseller.user.dto.request.*;
import com.conseller.conseller.user.dto.response.InfoValidationRequest;
import com.conseller.conseller.user.dto.response.LoginResponse;
import com.conseller.conseller.user.dto.response.UserInfoResponse;

import java.util.List;

public interface UserService {

    public User register(SignUpRequest signUpRequest);
    public InfoValidationRequest checkNickname(String nickname);
    public InfoValidationRequest checkId(String id);
    public InfoValidationRequest checkEmail(String email);
    public InfoValidationRequest checkPhoneNumber(String phoneNumber);
    public LoginResponse login(LoginRequest loginRequest);
    public void updateUserInfo(long userIdx, UserInfoRequest userInfoRequest);
    public UserInfoResponse getUserInfo(long userIdx);

    public void checkUserPassword(UserCheckPasswordRequest userCheckPasswordRequest);
    public void uploadUserProfile(UserProfileRequest userProfileRequest);
    public void deposit(long userIdx, int deposit);
    public List<Store> getUserStores(long userIdx);
    public List<Auction> getUserAuctions(long userIdx);
    public List<AuctionBid> getUserAuctionBids(long userIdx);
    public List<Barter> getUserbarters(long userIdx);
    public List<BarterRequest> getUserBarterRequests(long userIdx);
    public void deleteUser(long userIdx);
}
