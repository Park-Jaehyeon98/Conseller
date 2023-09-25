package com.conseller.conseller.notification.dto.mapper;

import com.conseller.conseller.entity.NotificationEntity;
import com.conseller.conseller.notification.dto.response.NotificationItemData;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    //NotificationList -> NotificationItemDataList 매핑
    @Named("N2N")
    NotificationItemData notificationToItemData(NotificationEntity notification);

    @IterableMapping(qualifiedByName = "N2N")
    List<NotificationItemData> notificationsToItemDatas(List<NotificationEntity> notificationEntityList);
}
