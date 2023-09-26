package com.conseller.conseller.notification;

import com.conseller.conseller.auction.auction.AuctionRepository;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.NotificationEntity;
import com.conseller.conseller.entity.Store;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.notification.dto.mapper.NotificationMapper;
import com.conseller.conseller.notification.dto.response.NotificationItemData;
import com.conseller.conseller.notification.dto.response.NotificationListResponse;
import com.conseller.conseller.store.StoreRepository;
import com.conseller.conseller.utils.DateTimeConverter;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NotificationServiceImpl implements NotificationService{
    private final AuctionRepository auctionRepository;
    private final StoreRepository storeRepository;
    private final NotificationRepository notificationRepository;
    private final DateTimeConverter dateTimeConverter;

    @Override
    public void sendAuctionNotification(Long auctionIdx, String title, String body, Integer index, Integer type) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException("없는 경매 글 입니다."));

        User user = null;

        if(index == 1) { // 구매자
            if(auction.getHighestBidUser().getFcm() == null)
                return;

            user = auction.getHighestBidUser();
        } else if( index == 2) { // 판매자
            if(auction.getUser().getFcm() == null)
                return;

            user = auction.getUser();
        }


        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(user.getFcm())
                .putData("timestamp", dateTimeConverter.convertString(LocalDateTime.now()))
                .build();

        try{
            String response = FirebaseMessaging.getInstance().send(message);

            log.info(response);

            //데이터베이스 저장
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setNotificationTitle(title);
            notificationEntity.setNotificationContent(body);
            notificationEntity.setNotificationType(type);
            if(index == 1) {
                notificationEntity.setSeller(false);
                notificationEntity.setUser(auction.getHighestBidUser());
            }else {
                notificationEntity.setSeller(true);
                notificationEntity.setUser(auction.getUser());
            }

            notificationRepository.save(notificationEntity);

        }catch (Exception e){
            log.warn(auction.getUser().getUserId() + ": 알림 전송에 실패하였습니다.");
        }
    }

    @Override
    public void sendStoreNotification(Long storeIdx, String title, String body, Integer index, Integer type) {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new RuntimeException("없는 스토어 글 입니다."));

        User user = null;

        if(index == 1) { // 구매자
            if(store.getConsumer().getFcm() == null)
                return;

            user = store.getConsumer();
        } else if( index == 2) { // 판매자
            if(store.getUser().getFcm() == null)
                return;

            user = store.getUser();
        }


        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(user.getFcm())
                .putData("timestamp", dateTimeConverter.convertString(LocalDateTime.now()))
                .build();

        try{
            String response = FirebaseMessaging.getInstance().send(message);

            log.info(response);

            //데이터베이스 저장
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setNotificationTitle(title);
            notificationEntity.setNotificationContent(body);
            notificationEntity.setNotificationType(type);
            if(index == 1) {
                notificationEntity.setSeller(false);
                notificationEntity.setUser(store.getConsumer());
            }else {
                notificationEntity.setSeller(true);
                notificationEntity.setUser(store.getUser());
            }

            notificationRepository.save(notificationEntity);


        }catch (Exception e){
            log.warn(store.getUser().getUserId() + ": 알림 전송에 실패하였습니다.");
        }
    }

    @Override
    public void sendBarterNotification(Long barterIdx, String title, String body, Integer index, Integer type) {

    }

    @Override
    public NotificationListResponse getNotificationList(Long userIdx) {
        List<NotificationEntity> notificationEntityList = notificationRepository.findByUser_UserIdx(userIdx);

        List<NotificationItemData> notificationItemDataList = NotificationMapper.INSTANCE.notificationsToItemDatas(notificationEntityList);

        NotificationListResponse response = new NotificationListResponse(notificationItemDataList);

        return response;
    }


}
