package com.conseller.conseller.entity;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto.BarterHostItemDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.response.BarterDetailResponseDTO;
import com.conseller.conseller.barter.barter.barterDto.response.BarterItemData;
import com.conseller.conseller.barter.barter.barterDto.response.BarterResponseDTO;
import com.conseller.conseller.barter.barter.enums.BarterStatus;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestResponseDto;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.user.dto.response.UserInfoResponse;
import com.conseller.conseller.utils.DateTimeConverter;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "barterIdx")
public class Barter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barterIdx;

    @Column(name = "barter_name", nullable = false)
    private String barterName;

    @Column(name = "barter_text", nullable = false)
    private String barterText;

    @CreatedDate
    private LocalDateTime barterCreatedDate;

    @Column(name = "barter_end_date", nullable = false)
    private LocalDateTime barterEndDate;

    @LastModifiedDate
    private LocalDateTime barterModifiedDate;

    @Column(name = "barter_completed_date")
    private LocalDateTime barterCompletedDate;

 // Enum의 문자열 값을 데이터베이스에 저장
    private String barterStatus = BarterStatus.EXCHANGEABLE.getStatus();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_idx")
    private User barterHost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complete_guest_idx")
    private User barterCompleteGuest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_idx")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_catergory_idx")
    private SubCategory preferSubCategory;

    @OneToMany(mappedBy = "barter")
    List<BarterRequest> barterRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "barter")
    List<BarterHostItem> barterHostItemList = new ArrayList<>();

    @Builder
    public Barter(String barterName, String barterText, LocalDateTime barterEndDate, User barterHost, SubCategory subCategory, SubCategory preferSubCategory) {
        this.barterName = barterName;
        this.barterText = barterText;
        this.barterCreatedDate = now();
        this.barterEndDate = barterEndDate;
        this.barterHost = barterHost;
        this.subCategory = subCategory;
        this.preferSubCategory = preferSubCategory;
    }

    public static BarterItemData toBarterItemData(Barter barter) {

        List<BarterHostItem> hostGifticons = barter.getBarterHostItemList();

        String gifticonName = "";
        String gifticonEndDate = "";
        String gifticonDataImageName = "";

        LocalDateTime recentExpireDate = null;
        for(BarterHostItem hostGifticon : hostGifticons) {
            Gifticon gift = hostGifticon.getGifticon();
            if(recentExpireDate == null || gift.getGifticonEndDate().isBefore(recentExpireDate)){
                recentExpireDate = gift.getGifticonEndDate();
                gifticonEndDate = DateTimeConverter.getInstance().convertString(gift.getGifticonEndDate());
                gifticonName = gift.getGifticonName();
                gifticonDataImageName = gift.getGifticonDataImageUrl();
            }
        }

        //디포짓
        Long userDeposit = barter.getBarterHost().getUserDeposit();
        Boolean deposit = false;
        if(userDeposit > 0) deposit = true;

        //선호
        String preper = barter.getPreferSubCategory().getSubCategoryContent();

        //barter 이름
        String barterName = barter.getBarterName();


        return BarterItemData.builder()
                .barterIdx(barter.getBarterIdx())
                .gifticonDataImageName(gifticonDataImageName)
                .gifticonName(gifticonName)
                .gifticonEndDate(gifticonEndDate)
                .deposit(deposit)
                .preper(preper)
                .barterName(barterName)
                .build();
    }

    public BarterResponseDTO toBarterResponseDto(Barter barter){
        List<BarterHostItemDto> barterHostItemDtoList= new ArrayList<>();
        for(BarterHostItem bhi : barter.getBarterHostItemList()) {
            BarterHostItemDto bhiDto = bhi.toBarterHostItemDto(bhi);
            barterHostItemDtoList.add(bhiDto);
        }

        List<BarterRequestResponseDto> barterRequestResponseDtoList = new ArrayList<>();
        for(BarterRequest bri : barter.getBarterRequestList()) {
            BarterRequestResponseDto briDto = bri.toBarterRequestResponseDto(bri);
            barterRequestResponseDtoList.add(briDto);
        }

        User host = barter.getBarterHost();
        User guest = barter.getBarterCompleteGuest();

        UserInfoResponse hostUserInfoResponse = UserInfoResponse.builder()
                .userId(host.getUserId())
                .userEmail(host.getUserEmail())
                .userNickname(host.getUserNickname())
                .userProfileUrl(host.getUserProfileUrl())
                .build();

        UserInfoResponse guestUserInfoResponse = null;

        if(barter.getBarterCompleteGuest() != null) {
            guestUserInfoResponse = UserInfoResponse.builder()
                    .userId(guest.getUserId())
                    .userEmail(guest.getUserEmail())
                    .userNickname(guest.getUserNickname())
                    .userProfileUrl(guest.getUserProfileUrl())
                    .build();
        }

        return BarterResponseDTO.builder()
                .barterIdx(barter.getBarterIdx())
                .barterName(barter.getBarterName())
                .barterText(barter.getBarterText())
                .barterCreatedDate(DateTimeConverter.getInstance().convertString(barter.getBarterCreatedDate()))
                .barterEndDate(DateTimeConverter.getInstance().convertString(barter.getBarterEndDate()))
                .subCategory(barter.getSubCategory().getSubCategoryContent())
                .preferSubCategory(barter.getPreferSubCategory().getSubCategoryContent())
                .barterHost(hostUserInfoResponse)
                .barterCompleteGuest(guestUserInfoResponse)
                .barterRequestResponseDtoList(barterRequestResponseDtoList)
                .barterHostItemDtoList(barterHostItemDtoList)
                .build();
    }

    public void modifyBarter(BarterModifyRequestDto barterModifyRequestDto, SubCategory preferSubCategory) {
        this.barterName = barterModifyRequestDto.getBarterName();
        this.barterText = barterModifyRequestDto.getBarterText();
        this.preferSubCategory = preferSubCategory;

    }

    public BarterItemData toBarterItemData(Barter barter, Gifticon gifticon, Boolean deposit) {
        return BarterItemData.builder()
                .barterIdx(barter.getBarterIdx())
                .gifticonDataImageName(gifticon.getGifticonDataImageUrl())
                .gifticonName(gifticon.getGifticonName())
                .gifticonEndDate(DateTimeConverter.getInstance().convertString(gifticon.getGifticonEndDate()))
                .barterEndDate(DateTimeConverter.getInstance().convertString(barter.getBarterEndDate()))
                .deposit(deposit)
                .preper(barter.getPreferSubCategory().getSubCategoryContent())
                .barterName(barter.getBarterName())
                .build();
    }
}

