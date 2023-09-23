package com.conseller.conseller.notification;

public interface NotificationService {
    public void sendAuctionNotification(Long auctionIdx, String title, String body);

}
