package com.conseller.conseller.gifticon.service;

import com.conseller.conseller.category.mainCategory.MainCategoryRepository;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.MainCategory;
import com.conseller.conseller.entity.SubCategory;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.gifticon.dto.request.GifticonRegisterRequest;
import com.conseller.conseller.gifticon.dto.response.ImageUrlsResponse;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.utils.DateTimeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifticonServiceImpl implements GifticonService {

    private final GifticonRepository gifticonRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final UserRepository userRepository;
    private final DateTimeConverter dateTimeConverter;

    public GifticonResponse getGifticonResponse(long gifticonIdx) {
        Gifticon gifticon = gifticonRepository.findByGifticonIdx(gifticonIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘 입니다."));

        return GifticonResponse.builder()
                .gifticonIdx(gifticon.getGifticonIdx())
                .gifticonBarcode(gifticon.getGifticonBarcode())
                .gifticonName(gifticon.getGifticonName())
                .gifticonStartDate(dateTimeConverter.convertString(gifticon.getGifticonStartDate()))
                .gifticonEndDate(dateTimeConverter.convertString(gifticon.getGifticonEndDate()))
                .gifticonAllImageUrl(gifticon.getGifticonAllImageUrl())
                .gifticonDataImageUrl(gifticon.getGifticonDataImageUrl())
                .gifticonStatus(gifticon.getGifticonStatus())
                .userIdx(gifticon.getUser().getUserIdx())
                .subCategoryIdx(gifticon.getSubCategory().getSubCategoryIdx())
                .mainCategoryIdx(gifticon.getMainCategory().getMainCategoryIdx())
                .build();
    }

    @Override
    public void registGifticon(long userIdx, GifticonRegisterRequest gifticonRegisterRequest, String allImageUrl, String dataImageUrl) {

        //카테고리 엔티티를 가져온다.
        SubCategory subCategory = subCategoryRepository.findBySubCategoryIdx(gifticonRegisterRequest.getSubCategory())
                .orElseThrow(() -> new RuntimeException("유효하지 않은 서브 카테고리 입니다."));
        MainCategory mainCategory = mainCategoryRepository.findByMainCategoryIdx(gifticonRegisterRequest.getMainCategory())
                .orElseThrow(() -> new RuntimeException("유효하지 않은 메인 카테고리 입니다."));
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 유저 idx 값 입니다."));


        Gifticon gifticon = Gifticon.builder()
                .gifticonBarcode(gifticonRegisterRequest.getGifticonBarcode())
                .gifticonName(gifticonRegisterRequest.getGifticonName())
                .gifticonStatus(GifticonStatus.KEEP.getStatus())
                .gifticonAllImageUrl(allImageUrl)
                .gifticonDataImageUrl(dataImageUrl)
                .subCategory(subCategory)
                .mainCategory(mainCategory)
                .gifticonEndDate(dateTimeConverter.convertDateTime(gifticonRegisterRequest.getGifticonEndDate()))
                .user(user)
                .build();

        gifticonRepository.save(gifticon);

        log.info("기프티콘 등록 완료");
    }

    @Override
    public ImageUrlsResponse deleteGifticon(long gifticonIdx) {
        Gifticon gifticon = gifticonRepository.findByGifticonIdx(gifticonIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘 입니다."));

        String gifticonAllImageUrl = gifticon.getGifticonAllImageUrl();
        String gifticonDataImageUrl = gifticon.getGifticonDataImageUrl();

        gifticonRepository.delete(gifticon);

        return ImageUrlsResponse.builder()
                .gifticonAllImageUrl(gifticonAllImageUrl)
                .gifticonDataImageUrl(gifticonDataImageUrl)
                .build();
    }
}
