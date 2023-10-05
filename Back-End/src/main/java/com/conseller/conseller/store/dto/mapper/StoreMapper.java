package com.conseller.conseller.store.dto.mapper;

import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Store;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.store.dto.request.RegistStoreRequest;
import com.conseller.conseller.store.dto.response.DetailStoreResponse;
import com.conseller.conseller.store.dto.response.StoreConfirmResponse;
import com.conseller.conseller.store.dto.response.StoreItemData;
import com.conseller.conseller.utils.DateTimeConverter;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel="spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    //RegistSaleRequest -> Store 매핑
    @Mapping(source = "user", target = "user")
    @Mapping(source = "gifticon", target = "gifticon")
    Store registStoreRequestToStore(RegistStoreRequest request, User user, Gifticon gifticon);

    //User, Store -> DetailStoreResponse 매핑
    default DetailStoreResponse entityToDetailStoreResponse(Store store) {
        DetailStoreResponse response = new DetailStoreResponse();

        response.setPostContent(store.getStoreText());
        response.setStoreUserIdx(store.getUser().getUserIdx());
        response.setStoreUserNickname(store.getUser().getUserNickname());
        response.setStoreUserProfileUrl(store.getUser().getUserProfileUrl());
        response.setStoreUserDeposit(store.getUser().getUserDeposit());
        response.setStoreIdx(store.getStoreIdx());
        response.setGifticonDataImageName(store.getGifticon().getGifticonDataImageUrl());
        response.setGifticonName(store.getGifticon().getGifticonName());
        response.setGifticonEndDate(DateTimeConverter.getInstance().convertString(store.getGifticon().getGifticonEndDate()));
        response.setStoreEndDate(DateTimeConverter.getInstance().convertString(store.getStoreEndDate()));
        response.setDeposit(false);
        response.setStorePrice(store.getStorePrice());

        return response;
    }

    //StoreList -> StoreItemDataList 매핑
    @Named("S2S")
    default StoreItemData storeToItemData(Store store) {
        StoreItemData itemData = new StoreItemData();

        itemData.setStoreIdx(store.getStoreIdx());
        itemData.setGifticonDataImageName(store.getGifticon().getGifticonDataImageUrl());
        itemData.setGifticonName(store.getGifticon().getGifticonName());
        itemData.setGifticonEndDate(DateTimeConverter.getInstance().convertString(store.getGifticon().getGifticonEndDate()));
        itemData.setStoreEndDate(DateTimeConverter.getInstance().convertString(store.getStoreEndDate()));
        itemData.setStoreStatus(store.getStoreStatus());
        itemData.setDeposit(false);
        itemData.setStorePrice(store.getStorePrice());

        return itemData;
    }

    @IterableMapping(qualifiedByName = "S2S")
    List<StoreItemData> storesToItemDatas(List<Store> storeList);

    //Store -> StoreConfirm 매핑
    default StoreConfirmResponse storeToComfirm(Store store) {
        StoreConfirmResponse response = new StoreConfirmResponse();

        response.setGifticonDataImageName(store.getGifticon().getGifticonDataImageUrl());
        response.setNotificationCreatedDate(DateTimeConverter.getInstance().convertString(LocalDateTime.now()));
        response.setGiftconName(store.getGifticon().getGifticonName());
        response.setStorePrice(store.getStorePrice());
        response.setPostContent(store.getStoreText());
        response.setBuyUserImageUrl(store.getConsumer().getUserProfileUrl());
        response.setBuyUserNickname(store.getConsumer().getUserNickname());
        response.setBuyUserName(store.getConsumer().getUsername());
        response.setBuyUserIdx(store.getUser().getUserIdx());

        return response;
    }
}
