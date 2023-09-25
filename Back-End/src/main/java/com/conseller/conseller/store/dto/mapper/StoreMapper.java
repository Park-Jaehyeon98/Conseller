package com.conseller.conseller.store.dto.mapper;

import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Store;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.store.dto.request.RegistStoreRequest;
import com.conseller.conseller.store.dto.response.DetailStoreResponse;
import com.conseller.conseller.store.dto.response.StoreItemData;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    //RegistSaleRequest -> Store 매핑
    @Mapping(source = "user", target = "user")
    @Mapping(source = "gifticon", target = "gifticon")
    Store registStoreRequestToStore(RegistStoreRequest request, User user, Gifticon gifticon);

    //User, Store -> DetailStoreResponse 매핑
    @Mapping(source = "user.userIdx", target = "storeUserIdx")
    @Mapping(source = "user.userNickname", target = "storeUserNickname")
    @Mapping(source = "user.userProfileUrl", target = "storeUserProfileUrl")
    @Mapping(source = "user.userDeposit", target = "storeUserDeposit")
    @Mapping(source = "store.storeText", target = "postContent")
    DetailStoreResponse entityToDetailStoreResponse(User user, Store store);

    //StoreList -> StoreItemDataList 매핑
    @Named("S2S")
    default StoreItemData storeToItemData(Store store) {
        StoreItemData itemData = new StoreItemData();

        itemData.setStoreIdx(store.getStoreIdx());
        itemData.setGifticonDataImageName(store.getGifticon().getGifticonDataImageUrl());
        itemData.setGifticonName(store.getGifticon().getGifticonName());
        itemData.setGifticonEndDate(store.getGifticon().getGifticonEndDate());
        itemData.setStoreEndDate(store.getStoreEndDate());
        itemData.setPopular("0");
        itemData.setStorePrice(store.getStorePrice());

        return itemData;
    }

    @IterableMapping(qualifiedByName = "S2S")
    List<StoreItemData> storesToItemDatas(List<Store> storeList);
}
