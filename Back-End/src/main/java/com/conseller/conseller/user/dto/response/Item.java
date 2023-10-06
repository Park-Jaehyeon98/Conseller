package com.conseller.conseller.user.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Item<T> {
    T items;
}
