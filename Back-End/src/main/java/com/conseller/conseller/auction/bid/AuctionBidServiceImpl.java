package com.conseller.conseller.auction.bid;

import com.conseller.conseller.auction.auction.AuctionRepository;
import com.conseller.conseller.auction.auction.enums.AuctionStatus;
import com.conseller.conseller.auction.bid.dto.mapper.AuctionBidMapper;
import com.conseller.conseller.auction.bid.dto.request.AuctionBidRequest;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

        if(auction.getAuctionStatus().equals(AuctionStatus.IN_PROGRESS.getStatus())) {

            if (request.getAuctionBidPrice() < auction.getLowerPrice() || request.getAuctionBidPrice() > auction.getUpperPrice()) {
                throw new CustomException(CustomExceptionStatus.INVALID_BID_RANGE);
            }

            // 현재 최대 입찰금보다 지금 입찰한 금액이 더 크다면 최대 입찰금, 최대 금액 입찰한 유저 갱신
            if (request.getAuctionBidPrice() > auction.getAuctionHighestBid()) {
                auction.setAuctionHighestBid(request.getAuctionBidPrice());
                auction.setHighestBidUser(user);
            }

            boolean isExist = false;
            Long bidIdx = 0L;

            // 이미 입찰이 있고 그 입찰이 지금 경매라면
            for (AuctionBid bid : auction.getAuctionBidList()) {
                if (bid.getUser().getUserIdx().equals(request.getUserIdx())) {
                    isExist = true;
                    bidIdx = bid.getAuctionBidIdx();
                }
            }

            if (isExist) {
                AuctionBid auctionBid = auctionBidRepository.findById(bidIdx)
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_BID_INVALID));

                // 입찰 정보 수정
                auctionBid.setAuctionBidPrice(request.getAuctionBidPrice());
                auctionBid.setAuction(auction);
            } else { // 없으면
                AuctionBid auctionBid = AuctionBidMapper.INSTANCE.registRequestToAuctionBid(user, auction, request);

                // 새로 등록
                auctionBidRepository.save(auctionBid);
            }
        }
        else {
            throw new CustomException(CustomExceptionStatus.ALREADY_TRADE_AUCTION);
        }

    }

    @Override
    public void deleteAuctionBid(Long auctionBidIdx) {
        AuctionBid auctionBid = auctionBidRepository.findById(auctionBidIdx)
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_BID_INVALID));

        // 삭제하려고 하는 입찰자가 최대 금액 입찰자라면 
        if(auctionBid.getAuction().getAuctionHighestBid().equals(auctionBid.getAuctionBidPrice())) {
            Auction auction = auctionRepository.findById(auctionBid.getAuction().getAuctionIdx())
                    .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

            // 입찰 목록을 입찰 금액에 내림차순으로 정렬해서 가져옴-
            List<AuctionBid> auctionBidList = auctionBidRepository
                    .findByAuctionIdxOrderByAuctionBidPriceDesc(auction.getAuctionIdx());
            
            //입잘자가 혼자라면 초기화
            if(auctionBidList.size() == 1){
                auction.setAuctionHighestBid(0);
                auction.setHighestBidUser(null);
            }else { //입찰자보다 바로 아래 입찰금액으로 갱신
                auction.setAuctionHighestBid(auctionBidList.get(1).getAuctionBidPrice());
                auction.setHighestBidUser(auctionBidList.get(1).getUser());
            }

        }

        auctionBidRepository.deleteById(auctionBidIdx);
    }
}
