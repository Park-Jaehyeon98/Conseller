package com.conseller.conseller.store.dto.mapper;

import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Store;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.store.dto.request.RegistStoreRequest;
import com.conseller.conseller.store.dto.response.DetailStoreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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
    DetailStoreResponse entityToDetailStoreResponse(User user, Store store);

}
