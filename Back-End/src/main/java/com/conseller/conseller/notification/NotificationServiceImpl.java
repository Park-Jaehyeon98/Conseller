package com.conseller.conseller.notification;

import com.conseller.conseller.auction.auction.AuctionRepository;
import com.conseller.conseller.barter.barter.BarterRepository;
import com.conseller.conseller.barter.barter.enums.BarterStatus;
import com.conseller.conseller.barter.barterRequest.BarterRequestRepository;
import com.conseller.conseller.barter.barterRequest.enums.RequestStatus;
import com.conseller.conseller.entity.*;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
import com.conseller.conseller.notification.dto.mapper.NotificationMapper;
import com.conseller.conseller.notification.dto.request.NotificationAnswerRequest;
import com.conseller.conseller.notification.dto.response.NotificationItemData;
import com.conseller.conseller.notification.dto.response.NotificationListResponse;
import com.conseller.conseller.store.StoreRepository;
import com.conseller.conseller.user.UserRepository;
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
    private final UserRepository userRepository;
    private final BarterRequestRepository barterRequestRepository;
    private final BarterRepository barterRepository;

    @Override
    public void sendAuctionNotification(Long auctionIdx, String title, String body, Integer index, Integer type) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

        User user = null;
        String contents = null;

        if(index == 1) { // 구매자
            if(auction.getHighestBidUser().getFcm() == null)
                return;

            user = auction.getHighestBidUser();

            contents = user.getUserNickname() + " " + body;
        } else if( index == 2) { // 판매자
            if(auction.getUser().getFcm() == null)
                return;

            user = auction.getUser();

            contents = user.getUserNickname() + " " + body;
        } else {
            throw new CustomException(CustomExceptionStatus.INVALID_NOTI_TYPE);
        }


        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(contents)
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
            notificationEntity.setNotificationContent(contents);
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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.STORE_INVALID));

        User user = null;
        String contents = null;

        if(index == 1) { // 구매자
            if(store.getConsumer().getFcm() == null)
                return;

            user = store.getConsumer();

            contents = user.getUserNickname() + " " + body;
        } else if( index == 2) { // 판매자
            if(store.getUser().getFcm() == null)
                return;

            user = store.getUser();

            contents = user.getUserNickname() + " " + body;
        } else {
            throw new CustomException(CustomExceptionStatus.INVALID_NOTI_TYPE);
        }


        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(contents)
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
            notificationEntity.setNotificationContent(contents);
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
    public void sendBarterNotification(Long barterIdx, String title, Integer type) {
        List<BarterRequest> requestList = barterRequestRepository.findByBarterIdx(barterIdx);

        for(BarterRequest br : requestList) {
            if(br.getUser().getFcm() == null)
                return;

            String contents = null;

            if(br.getBarterRequestStatus().equals(RequestStatus.ACCEPTED.getStatus())){
                contents = br.getUser().getUserNickname() + " 님의 요청이 수락되었습니다.";
            }else {
                contents = br.getUser().getUserNickname() + " 님의 요청이 거절되었습니다.";
            }

            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(contents)
                    .build();

            Message message = Message.builder()
                    .setNotification(notification)
                    .setToken(br.getUser().getFcm())
                    .putData("timestamp", dateTimeConverter.convertString(LocalDateTime.now()))
                    .build();

            try{
                String response = FirebaseMessaging.getInstance().send(message);

                //데이터베이스 저장
                NotificationEntity notificationEntity = new NotificationEntity();
                notificationEntity.setNotificationTitle(title);
                notificationEntity.setNotificationContent(contents);
                notificationEntity.setNotificationType(type);
                notificationEntity.setSeller(false);
                notificationEntity.setUser(br.getUser());

                notificationRepository.save(notificationEntity);


            }catch (Exception e){
                log.warn(br.getUser().getUserId() + ": 알림 전송에 실패하였습니다.");
            }
        }
    }

    @Override
    public void sendBarterExpiredNotification(Long barterIdx, String title, Integer type) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환글입니다."));

        if(barter.getBarterHost().getFcm() == null)
            return;

        String contents = null;
        if(barter.getBarterStatus().equals(BarterStatus.EXPIRED.getStatus())) {
            contents = barter.getBarterName() + " 교환글이 만료되었습니다.";
        }

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(contents)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(barter.getBarterHost().getFcm())
                .putData("timestamp", dateTimeConverter.convertString(LocalDateTime.now()))
                .build();

        try{
            String response = FirebaseMessaging.getInstance().send(message);

            //데이터베이스 저장
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setNotificationTitle(title);
            notificationEntity.setNotificationContent(contents);
            notificationEntity.setNotificationType(type);
            notificationEntity.setSeller(false);
            notificationEntity.setUser(barter.getBarterHost());

            notificationRepository.save(notificationEntity);


        }catch (Exception e){
            log.warn(barter.getBarterHost().getUserId() + ": 알림 전송에 실패하였습니다.");
        }
    }

    @Override
    public void sendBarterRequestRejectedNotification(Long barterRequestIdx, String title, Integer type) {
        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환신청 입니다."));

        if(barterRequest.getUser().getFcm() == null) return;

        String contents = null;
        if(barterRequest.getBarterRequestStatus().equals(RequestStatus.REJECTED)) {
            contents = barterRequest.getBarter().getBarterName() + " 에 대한 교환 신청이 거절되었습니다.";
        }

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(contents)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(barterRequest.getUser().getFcm())
                .putData("timestamp", dateTimeConverter.convertString(LocalDateTime.now()))
                .build();

        try{
            String response = FirebaseMessaging.getInstance().send(message);

            //데이터베이스 저장
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setNotificationTitle(title);
            notificationEntity.setNotificationContent(contents);
            notificationEntity.setNotificationType(type);
            notificationEntity.setSeller(false);
            notificationEntity.setUser(barterRequest.getUser());

            notificationRepository.save(notificationEntity);


        }catch (Exception e){
            log.warn(barterRequest.getUser().getUserId() + ": 알림 전송에 실패하였습니다.");
        }

    }

    @Override
    public void sendBarterRequestNotification(Long barterIdx, String title, Integer type){
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환글입니다."));

        if(barter.getBarterHost().getFcm() == null) return;

        String contents = null;
        if(barter.getBarterStatus().equals(BarterStatus.SUGGESTED.getStatus())) {
            contents = barter.getBarterName() + " 에 대한 새로운 교환신청!";
        }

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(contents)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(barter.getBarterHost().getFcm())
                .putData("timestamp", dateTimeConverter.convertString(LocalDateTime.now()))
                .build();

        try{
            String response = FirebaseMessaging.getInstance().send(message);

            //데이터베이스 저장
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setNotificationTitle(title);
            notificationEntity.setNotificationContent(contents);
            notificationEntity.setNotificationType(type);
            notificationEntity.setSeller(false);
            notificationEntity.setUser(barter.getBarterHost());

            notificationRepository.save(notificationEntity);


        }catch (Exception e){
            log.warn(barter.getBarterHost().getUserId() + ": 알림 전송에 실패하였습니다.");
        }
    }

    @Override
    public void sendGifticonNotification(Long userIdx, Integer remainDay, String gifticionName, Integer gifticonCount, Integer type) {
        User user = userRepository.findById(userIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));

        if(user.getFcm() == null)
            return;

        String title = "기프티콘 알림";
        String body = " ";

        if(gifticonCount == 1){
            body = gifticionName + " 기프티콘 유효기간이 " + remainDay + "일 남았습니다.";
        }else {
            body = gifticionName + " 외 " + (gifticonCount - 1) + "개의 기프티콘 유효기간이 " + remainDay + "일 남았습니다.";
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

            //데이터베이스 저장
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setNotificationTitle(title);
            notificationEntity.setNotificationContent(body);
            notificationEntity.setNotificationType(type);
            notificationEntity.setSeller(false);
            notificationEntity.setUser(user);

            notificationRepository.save(notificationEntity);


        }catch (Exception e){
            log.warn(user.getUserId() + ": 알림 전송에 실패하였습니다.");
        }
    }


    @Override
    public void sendNotification(Long userIdx, String title, String body) {
        User user = userRepository.findById(userIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));

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
            notificationEntity.setNotificationType(1);
            notificationEntity.setSeller(true);
            notificationEntity.setUser(user);


            notificationRepository.save(notificationEntity);


        }catch (Exception e){
            log.warn(user.getUserId() + ": 알림 전송에 실패하였습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationListResponse getNotificationList(Long userIdx) {
        List<NotificationEntity> notificationEntityList = notificationRepository.findByUser_UserIdx(userIdx);

        List<NotificationItemData> notificationItemDataList = NotificationMapper.INSTANCE.notificationsToItemDatas(notificationEntityList);

        NotificationListResponse response = new NotificationListResponse(notificationItemDataList);

        return response;
    }

    @Override
    public void getAnswer(Long userIdx, NotificationAnswerRequest request) {
        NotificationEntity notification = notificationRepository.findById(request.getNotificationIdx())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 알림입니다."));

        //알림 로직
        if(request.getNotificationAnswer()){
            notification.setNotificationType(6);
        }
        else {
            notificationRepository.deleteById(request.getNotificationIdx());
        }
    }


}
