package com.conseller.conseller.barter.barterRequest.barterRequestDto;

import com.conseller.conseller.barter.barter.barterDto.response.MyBarterResponseDto;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MyBarterRequestResponseDto {

    private Long barterRequestIdx;

    private String barterRequestStatus;

    private long barterIdx;

    private String barterName;

    private String barterStatus;

    private MyBarterResponseDto myBarterResponseDto;

    private List<GifticonResponse> barterGuestItems;

}
