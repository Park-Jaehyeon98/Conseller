package com.conseller.conseller.notification;

import com.conseller.conseller.notification.dto.response.NotificationListResponse;

public interface NotificationService {
    public void sendAuctionNotification(Long auctionIdx, String title, String body, Integer index);

    public void sendStoreNotification(Long storeIdx, String title, String body, Integer index);

    public void sendBarterNotification(Long barterIdx, String title, String body, Integer index);

    public NotificationListResponse getNotificationList(Long userIdx);
}
