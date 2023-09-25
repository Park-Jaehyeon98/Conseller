package com.conseller.conseller.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeConverter {

    public LocalDateTime convertDateTime(String date) {

        // 문자열을 LocalDate로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        // LocalDate를 LocalDateTime으로 변환 (시간을 23:59:59로 설정)
        return localDate.atTime(23, 59, 59);
    }

    public String convertString(LocalDateTime dateTime) {

        //LocaldateTime 포맷터 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");

        return dateTime.format(formatter);
    }
}
