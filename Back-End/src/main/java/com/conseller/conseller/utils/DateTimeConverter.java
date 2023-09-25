package com.conseller.conseller.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeConverter {

    private static final DateTimeConverter INSTANCE = new DateTimeConverter();

    public static DateTimeConverter getInstance() {
        return INSTANCE;
    }

    public LocalDateTime convertDateTime(String date) {

        // 문자열을 LocalDate로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        // LocalDate를 LocalDateTime으로 변환 (시간을 23:59:59로 설정)
        return localDate.atTime(23, 59, 59);
    }

    public LocalDateTime convertLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");
        LocalDateTime date = LocalDateTime.parse(dateTime, formatter);

        return date;
    }

    public String convertString(LocalDateTime dateTime) {

        //LocaldateTime 포맷터 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");

        return dateTime.format(formatter);
    }
}
