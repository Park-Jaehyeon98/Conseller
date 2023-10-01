package com.conseller.conseller.user.enums;

import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AccountBanks {
    SHINHAN("신한은행"),
    HANA("하나은행"),
    NH("농협은행"),
    KB("국민은행"),
    WOORI("우리은행");

    private final String bank;

    public static AccountBanks fromString(String bank) {

        return Arrays.stream(AccountBanks.values())
                .filter(accountBanks -> accountBanks.getBank().equals(bank))
                .findAny()
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_BANK_INVALID));
    }
}
