package com.conseller.conseller.entity;

import com.conseller.conseller.barter.barter.barterDto.response.BarterResponseDto;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestResponseDto;
import com.conseller.conseller.barter.barterRequest.enums.RequestStatus;
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

    @Enumerated(EnumType.STRING)
    private RequestStatus barterRequestStatus;

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
        this.barterRequestStatus = RequestStatus.WAIT;
        this.barter = barter;
        this.user = user;
    }

    public BarterRequestResponseDto toBarterRequestResponseDto(BarterRequest barterRequest, BarterResponseDto barterResponseDto) {
        return BarterRequestResponseDto.builder()
                .barterRequestStatus(barterRequest.getBarterRequestStatus())
                .barterResponse(barterResponseDto)
                .user(barterRequest.getUser())
                .build();
    }
}
