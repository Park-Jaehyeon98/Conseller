package com.conseller.conseller.auction.auction;

import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuctionScheduler {
    private final AuctionService auctionService;
    private final NotificationService notificationService;

    @Async
    @Scheduled(cron = "0 */15 * * * ?")
    public void autoAuctionConfirm() {
        List<Auction> auctions = auctionService.getAuctionConfirmList();

        for(Auction auction : auctions) {
            auctionService.confirmAuction(auction.getAuctionIdx());

            notificationService.sendAuctionNotification(auction.getAuctionIdx(),"경매 거래 확정", "님과의 거래가 자동으로 확정되었습니다.", 2,1);
        }
    }

}
