package com.conseller.conseller.entity;

import com.conseller.conseller.barter.barter.barterDto.response.BarterResponseDto;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestResponseDto;
import com.conseller.conseller.barter.barterRequest.enums.RequestStatus;
import com.conseller.conseller.user.dto.response.UserInfoResponse;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "barterRequestIdx")
public class BarterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barterRequestIdx;

    @Column(name = "barter_request_status")
    private String barterRequestStatus = RequestStatus.WAIT.getStatus();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barter_idx", nullable = false)
    private Barter barter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;

    @OneToMany(mappedBy = "barterRequest")
    List<BarterGuestItem> barterGuestItemList = new ArrayList<>();

    @Builder
    public BarterRequest(Barter barter, User user){
        this.barterRequestStatus = RequestStatus.WAIT.getStatus();
        this.barter = barter;
        this.user = user;
    }

    public BarterRequestResponseDto toBarterRequestResponseDto(BarterRequest barterRequest) {
        User user = barterRequest.getUser();
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .userEmail(user.getUserEmail())
                .userProfileUrl(user.getUserProfileUrl())
                .userAccount(user.getUserAccount())
                .userAccountBank(user.getUserAccountBank())
                .userPhoneNumber(user.getUserPhoneNumber())
                .build();

        return BarterRequestResponseDto.builder()
                .barterIdx(barterRequest.getBarterRequestIdx())
                .barterRequestStatus(barterRequest.getBarterRequestStatus())
                .barterIdx(barterRequest.getBarter().getBarterIdx())
                .user(userInfoResponse)
                .build();
    }
}
