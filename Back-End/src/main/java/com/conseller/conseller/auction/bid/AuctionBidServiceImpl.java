package com.conseller.conseller.auction.bid;

import com.conseller.conseller.auction.auction.AuctionRepository;
import com.conseller.conseller.auction.bid.dto.mapper.AuctionBidMapper;
import com.conseller.conseller.auction.bid.dto.request.AuctionBidRequest;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuctionBidServiceImpl implements AuctionBidService{

    private final AuctionBidRepository auctionBidRepository;
    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;

    @Override
    public void registAuctionBid(Long auctionIdx, AuctionBidRequest request) {
        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new RuntimeException());
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException());

        if(request.getAuctionBidPrice() < auction.getLowerPrice() || request.getAuctionBidPrice() > auction.getUpperPrice()) {
            // 입찰금 범위 이상 예외처리
        }

        if(request.getAuctionBidPrice() > auction.getAuctionHighestBid()) {
            auction.setAuctionHighestBid(request.getAuctionBidPrice());
        }

        AuctionBid auctionBid = AuctionBidMapper.INSTANCE.registRequestToAuctionBid(user, auction, request);

        auctionBidRepository.save(auctionBid);
    }

    @Override
    public void deleteAuctionBid(Long auctionBidIdx) {
        AuctionBid auctionBid = auctionBidRepository.findById(auctionBidIdx)
                        .orElseThrow(() -> new RuntimeException());

        if(auctionBid.getAuction().getAuctionHighestBid().equals(auctionBid.getAuctionBidPrice())) {
            Auction auction = auctionRepository.findById(auctionBid.getAuction().getAuctionIdx())
                    .orElseThrow(() -> new RuntimeException());

            List<AuctionBid> auctionBidList = auctionBidRepository
                    .findByAuctionIdxOrderByAuctionBidPriceDesc(auction.getAuctionIdx());

            if(auctionBidList.size() == 1){
                auction.setAuctionHighestBid(0);
            }else {
                auction.setAuctionHighestBid(auctionBidList.get(1).getAuctionBidPrice());
            }

        }

        auctionBidRepository.deleteById(auctionBidIdx);
    }
}
