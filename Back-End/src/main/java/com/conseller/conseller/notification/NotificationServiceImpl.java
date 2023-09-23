package com.conseller.conseller.notification;

import com.conseller.conseller.auction.auction.AuctionRepository;
import com.conseller.conseller.entity.Auction;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NotificationServiceImpl implements NotificationService{
    private final AuctionRepository auctionRepository;

    @Override
    public void sendAuctionNotification(Long auctionIdx, String title, String body) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException());

        if(auction.getUser().getFcm() == null)
            return;

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(auction.getUser().getFcm())
                .build();

        try{
            String response = FirebaseMessaging.getInstance().send(message);

            //데이터베이스 저장

        }catch (Exception e){
            log.warn(auction.getUser().getUserId() + ": 알림 전송에 실패하였습니다.");
        }
    }
}
