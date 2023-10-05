package com.conseller.conseller.store;

import com.conseller.conseller.entity.Store;
import com.conseller.conseller.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreScheduler {
    private final StoreService storeService;
    private final NotificationService notificationService;

    @Async
    @Scheduled(cron = "0 */15 * * * ?")
    public void autoStoreConfirm() {
        List<Store> stores = storeService.getStoreConfirmList();

        for(Store store : stores) {
            storeService.confirmStore(store.getStoreIdx());

            notificationService.sendStoreNotification(store.getStoreIdx(),"스토어 거래 확정", "님과의 거래가 자동으로 확정되었습니다.", 2,2);
        }
    }

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void autoStoreExpire() {
        List<Store> stores = storeService.getStoreExpiredList();

        for(Store store : stores) {
            storeService.rejectStore(store.getStoreIdx());
        }
    }
}
