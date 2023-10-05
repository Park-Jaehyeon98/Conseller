package com.conseller.conseller.gifticon.service;

import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.gifticon.dto.request.GifticonRegisterRequest;
import com.conseller.conseller.gifticon.dto.response.ImageUrlsResponse;

public interface GifticonService {
    public GifticonResponse getGifticonResponse(long gifticonIdx);

    public void registGifticon(long userIdx, GifticonRegisterRequest gifticonRegisterRequest, String allImageUrl, String dataImageUrl);

    //기프티콘을 삭제하고 이미지 url을 넘긴다.
    public void deleteGifticon(long gifticonIdx);

    //정각에 기프티콘 유효기간 알람을 보내주는 기능
    public void checkGifticonEndDate();

}
