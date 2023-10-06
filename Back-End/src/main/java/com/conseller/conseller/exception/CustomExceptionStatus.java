package com.conseller.conseller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomExceptionStatus {
    //로그인 관련
    WRONG_ID(1000, "잘못된 아이디입니다."),
    WRONG_PW(1001, "잘못된 비밀번호입니다."),
    RESTRICT(1002, "제한된 유저입니다."),
    ACCESS_TOKEN_INVALID(1003, "유효하지 않은 엑세스 토큰입니다."),
    REFRESH_TOKEN_INVALID(1004, "유효하지 않은 리프레시 토큰입니다."),
    PATTERN_INVALID(1005, "잘못된 패턴입니다."),
    FIND_ID_INVALID(1006, "유효하지 않은 정보입니다."),
    FIND_PW_INVALID(1007, "유효하지 않은 정보입니다."),
    USER_INVALID(1008, "존재하지 않는 유저입니다."),
    ACCOUNT_BANK_INVALID(1009, "존재하지 않는 은행정보 입니다."),

    //경매 관련
    AUCTION_INVALID(1100, "존재하지 않는 경매입니다."),
    ALREADY_TRADE_AUCTION(1101, "이미 거래 중인 경매입니다."),
    AUCTION_BID_INVALID(1102, "존재하지 않는 입찰입니다."),
    INVALID_BID_RANGE(1103, "입찰금의 범위가 잘못되었습니다."),

    //물물교환 관련
    BARTER_INVALID(1200, "존재하지 않는 물물교환입니다."),
    BARTER_NO_ITEM(1201, "해당 기프티콘이 존재하지 않습니다."),
    BARTER_NO_SELECT(1202, "기프티콘을 선택하지 않았습니다."),

    BARTER_REQUEST_INVALID(1203, "존재하지 않는 물물교환 교환요청입니다."),
    BARTER_EXPIRED_INVALID(1204, "이미 완료 또는 만료된 물물교환입니다."),
    BARTER_ALREADY_SEND(1205, "이미 요청을 보낸 물물교환입니다."),



    //스토어 관련
    STORE_INVALID(1300, "존재하지 않는 판매입니다."),
    ALREADY_TRADE_STORE(1301, "이미 거래 중인 판매입니다."),

    //마이페이지 관련
    ALREADY_USE_GIFTICON(1400, "이미 사용된 기프티콘입니다."),
    ALREADY_REGIST_GIFTICON(1401, "이미 등록된 기프티콘입니다."),


    //알림 관련
    ALREADY_TRADE_BARTER(1500, "이미 거래된 물물교환입니다."),
    INVALID_NOTI_TYPE(1501, "적합하지 않은 알림 타입입니다."),

    //기프티콘 관련
    GIFTICON_INVALID(1600,"존재하지 않는 기프티콘입니다."),
    GIFTICON_NOT_KEEP(1601, "보관 중인 기프티콘이 아닙니다."),
    EXPIRED_GIFTICON(1602, "유효기간이 이미 지난 기프티콘 입니다."),

    //문의 관련
    INQUIRY_INVALID(1700, "존재하지 않는 문의입니다."),

    //카테고리 관련
    MAIN_CATEGORY_INVALID(1800, "존재하지 않는 대분류입니다."),
    SUB_CATEGORY_INVALID(1801, "존재하지 않는 대분류입니다.");

    //알림 관련

    private final Integer code;
    private final String message;
}
