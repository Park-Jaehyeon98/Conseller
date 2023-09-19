package com.conseller.conseller.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AccountBanks {
    SHINHAN("신한은행");

    private final String bank;

    public static AccountBanks fromString(String bank) {

        return Arrays.stream(AccountBanks.values())
                .filter(accountBanks -> accountBanks.getBank().equals(bank))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(bank + "는 유효하지 않은 은행 정보 입니다."));
    }
}
