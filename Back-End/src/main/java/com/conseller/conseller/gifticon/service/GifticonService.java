package com.conseller.conseller.gifticon.service;

import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.gifticon.dto.request.GifticonRegisterRequest;
import com.conseller.conseller.gifticon.dto.response.ImageUrlsResponse;

public interface GifticonService {
    public GifticonResponse getGifticonResponse(long gifticonIdx);

    public void registGifticon(GifticonRegisterRequest gifticonRegisterRequest, String allImageUrl, String dataImageUrl);

    //기프티콘을 삭제하고 이미지 url을 넘긴다.
    public ImageUrlsResponse deleteGifticon(long gifticonIdx);
}
