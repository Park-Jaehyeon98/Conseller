package com.conseller.conseller.gifticon.service;

import com.conseller.conseller.category.mainCategory.MainCategoryRepository;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
import com.conseller.conseller.entity.*;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
import com.conseller.conseller.gifticon.GifticonValidator;
import com.conseller.conseller.gifticon.dto.response.ExpiringGifticonResponse;
import com.conseller.conseller.gifticon.repository.GifticonRepository;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.gifticon.dto.request.GifticonRegisterRequest;
import com.conseller.conseller.gifticon.dto.response.ImageUrlsResponse;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.gifticon.repository.GifticonRepositoryImpl;
import com.conseller.conseller.gifticon.repository.UsedGifticonRepository;
import com.conseller.conseller.notification.NotificationService;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.utils.DateTimeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifticonServiceImpl implements GifticonService {

    private final GifticonRepository gifticonRepository;
    private final GifticonRepositoryImpl gifticonRepositoryImpl;
    private final GifticonValidator gifticonValidator;
    private final UsedGifticonRepository usedGifticonRepository;

    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryRepository mainCategoryRepository;

    private final UserRepository userRepository;

    private final NotificationService notificationService;

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

        //예외처리
//        gifticonValidator.isValidGiftion(gifticonRegisterRequest);


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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ALREADY_REGIST_GIFTICON));

        String gifticonAllImageUrl = gifticon.getGifticonAllImageUrl();
        String gifticonDataImageUrl = gifticon.getGifticonDataImageUrl();

        //여기서 usedGifticon entity 객체 생성해서 값 넣고 save
        UsedGifticon usedGifticon = UsedGifticon.builder()
                        .usedGifticonBarcode(gifticon.getGifticonBarcode())
                        .usedGifticonDate(LocalDateTime.now())
                        .build();

        usedGifticonRepository.save(usedGifticon);

        gifticonRepository.delete(gifticon);

        return ImageUrlsResponse.builder()
                .gifticonAllImageUrl(gifticonAllImageUrl)
                .gifticonDataImageUrl(gifticonDataImageUrl)
                .build();
    }

    @Async
    @Scheduled(cron = "0 0 * * * *")
    public void checkGifticonEndDate() {

        log.info(LocalDateTime.now() + " 기프티콘 유효기간 알림 작업 시작");

        //쿼리 dsl로 불러온다.
        List<ExpiringGifticonResponse> gifticons = gifticonRepositoryImpl.getUserIdxAndExpiringGifticonCount();

        //해당 유저에 대해 notification 서비스에 알림 요청을 보낸다.
        for (ExpiringGifticonResponse item : gifticons) {
            notificationService.sendGifticonNotification(item.getUserIdx(), item.getExpiryDay(), item.getGifticonName(), item.getGifticonCnt(), 1);
        }

        log.info(LocalDateTime.now() + " 기프티콘 유효기간 알림 작업 종료");
     }
}
