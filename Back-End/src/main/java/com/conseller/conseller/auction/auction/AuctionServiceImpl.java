package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.mapper.AuctionMapper;
import com.conseller.conseller.auction.auction.dto.request.AuctionListRequest;
import com.conseller.conseller.auction.auction.dto.request.ModifyAuctionRequest;
import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.*;
import com.conseller.conseller.auction.auction.enums.AuctionStatus;
import com.conseller.conseller.auction.bid.AuctionBidRepository;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuctionServiceImpl implements AuctionService{
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;
    private final AuctionBidRepository auctionBidRepository;
    private final AuctionRepositoryImpl auctionImplRepository;

    // 경매 목록
    @Override
    @Transactional(readOnly = true)
    public AuctionListResponse getAuctionList(AuctionListRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), 10);

        Page<Auction> auctions = auctionImplRepository.findAuctionList(request, pageable);

        List<AuctionItemData> auctionItemDataList = AuctionMapper.INSTANCE.auctionsToItemDatas(auctions.getContent());

        AuctionListResponse response = new AuctionListResponse(auctionItemDataList,
                auctions.getTotalElements(),
                auctions.getTotalPages());

        return response;
    }

    // 경매 글 등록
    @Override
    public void registAuction(RegistAuctionRequest request) {
        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new RuntimeException());
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx())
                .orElseThrow(() -> new RuntimeException());

        if(!gifticon.getGifticonStatus().equals(GifticonStatus.KEEP.getStatus())){
            //등록 x 예외처리
        }else {
            Auction auction = AuctionMapper.INSTANCE.registAuctionRequestToAuction(request, user, gifticon);

            gifticon.setGifticonStatus(GifticonStatus.AUCTION.getStatus());

            auctionRepository.save(auction);
        }
    }

    // 경매 글 상세보기
    @Override
    @Transactional(readOnly = true)
    public DetailAuctionResponse detailAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException());
        User user = userRepository.findById(auction.getUser().getUserIdx())
                .orElseThrow(() -> new RuntimeException());

        List<AuctionBidItemData> auctionBidItemDataList = AuctionMapper.INSTANCE.bidsToItemDatas(auction.getAuctionBidList());

        DetailAuctionResponse detailAuctionResponse = AuctionMapper.INSTANCE.entityToDetailAuctionResponse(user, auction, auctionBidItemDataList);

        return detailAuctionResponse;
    }

    // 경매 글 수정
    @Override
    public void modifyAuction(Long auctionIdx, ModifyAuctionRequest auctionRequest) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException());

        auction.setAuctionEndDate(auctionRequest.getAuctionEndDate());
        auction.setAuctionText(auctionRequest.getAuctionText());
    }

    // 경매 글 삭제
    @Override
    public void deleteAuction(Long auctionIdx) {
        auctionRepository.deleteById(auctionIdx);
    }

    // 경매 거래 진행
    @Override
    public AuctionTradeResponse tradeAuction(Long auctionIdx, Integer index) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException());

        if(index == 1) { // 경매
            //가장 높은 입찰자에게 알림
        }
        else if(index == 2) { // 즉시 구매
            // 판매자에게 알림
        }

        // 경매 상태 거래중으로 변경
        auction.setAuctionStatus(AuctionStatus.IN_TRADE.getStatus());

        // 판매자의 계좌번호와 은행 전달
        AuctionTradeResponse response = new AuctionTradeResponse(auction.getUser().getUserAccount(),
                auction.getUser().getUserAccountBank());


        return response;
    }

    // 경매 거래 취소
    @Override
    public void cancelAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException());

        // 거래 취소 알림

        // 경매 상태 진행 중으로 변경
        auction.setAuctionStatus(AuctionStatus.IN_PROGRESS.getStatus());

        //가장 높은 입찰 삭제
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

        auctionBidRepository.deleteByUser_UserIdx(auctionBidList.get(0).getUser().getUserIdx());

    }

    // 경매 입금 완료
    @Override
    public void completeAuction(Long auctionIdx) {
        // 판매자에게 알림
    }

    // 경매 거래 확정
    @Override
    public void confirmAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException());
        Gifticon gifticon = gifticonRepository.findById(auction.getGifticon().getGifticonIdx())
                .orElseThrow(() -> new RuntimeException());
        User user = userRepository.findById(auction.getHighestBidUser().getUserIdx())
                .orElseThrow(() -> new RuntimeException());

        auction.setAuctionStatus(AuctionStatus.AWARDED.getStatus());

        gifticon.setUser(user);

        // 구매자에게 알림?

    }
}
