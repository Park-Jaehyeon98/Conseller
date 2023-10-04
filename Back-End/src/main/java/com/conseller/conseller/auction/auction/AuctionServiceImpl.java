package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.mapper.AuctionMapper;
import com.conseller.conseller.auction.auction.dto.request.AuctionListRequest;
import com.conseller.conseller.auction.auction.dto.request.ModifyAuctionRequest;
import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.*;
import com.conseller.conseller.auction.auction.enums.AuctionStatus;
import com.conseller.conseller.auction.bid.AuctionBidRepository;
import com.conseller.conseller.auction.bid.dto.mapper.AuctionBidMapper;
import com.conseller.conseller.auction.bid.enums.BidStatus;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.gifticon.repository.GifticonRepository;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        Pageable pageable = PageRequest.of(request.getPage() - 1, 10);

        Page<Auction> auctions = auctionImplRepository.findAuctionList(request, pageable);

        List<AuctionItemData> auctionItemDataList = AuctionMapper.INSTANCE.auctionsToItemDatas(auctions.getContent());

        log.info("auctionItemDataList : " + auctionItemDataList.toString());

        AuctionListResponse response = new AuctionListResponse(auctionItemDataList,
                auctions.getTotalElements(),
                auctions.getTotalPages());

        log.info("response : " + response);

        return response;
    }

    // 경매 글 등록
    @Override
    public Long registAuction(RegistAuctionRequest request) {
        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));

        if(!gifticon.getGifticonStatus().equals(GifticonStatus.KEEP.getStatus())){
            throw new CustomException(CustomExceptionStatus.GIFTICON_NOT_KEEP);
        }else {
            Auction auction = AuctionMapper.INSTANCE.registAuctionRequestToAuction(request, user, gifticon);

            auction.setAuctionEndDate(gifticon.getGifticonEndDate());

            gifticon.setGifticonStatus(GifticonStatus.AUCTION.getStatus());

            Auction saveAuction = auctionRepository.save(auction);

            return saveAuction.getAuctionIdx();
        }
    }

    // 경매 글 상세보기
    @Override
    @Transactional(readOnly = true)
    public DetailAuctionResponse detailAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

        List<AuctionBidItemData> auctionBidItemDataList = AuctionMapper.INSTANCE.bidsToItemDatas(auction.getAuctionBidList());

        DetailAuctionResponse detailAuctionResponse = AuctionMapper.INSTANCE.entityToDetailAuctionResponse(auction, auctionBidItemDataList);

        return detailAuctionResponse;
    }

    // 경매 글 수정
    @Override
    public void modifyAuction(Long auctionIdx, ModifyAuctionRequest auctionRequest) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

        auction.setAuctionText(auctionRequest.getAuctionText());
    }

    // 경매 글 삭제
    @Override
    public void deleteAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));
        Gifticon gifticon = gifticonRepository.findById(auction.getGifticon().getGifticonIdx())
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));

        gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());


        auctionRepository.deleteById(auctionIdx);

    }

    // 경매 거래 진행
    @Override
    public AuctionTradeResponse tradeAuction(Long auctionIdx, Long userIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));
        User user = userRepository.findById(userIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));

        AuctionTradeResponse response = null;

        if(auction.getAuctionStatus().equals(AuctionStatus.IN_PROGRESS.getStatus())) {
            if (!auction.getUser().getUserIdx().equals(userIdx)) { //즉시 구매자 라면
                log.info("immediate");

                auction.setAuctionHighestBid(auction.getUpperPrice());
                auction.setHighestBidUser(user);

                boolean isExist = false;
                Long bidIdx = 0L;

                // 이미 입찰이 있고 그 입찰이 지금 경매라면
                for (AuctionBid bid : auction.getAuctionBidList()) {
                    if (bid.getUser().getUserIdx().equals(userIdx)) {
                        isExist = true;
                        bidIdx = bid.getAuctionBidIdx();
                    }
                }

                if (isExist) {
                    AuctionBid auctionBid = auctionBidRepository.findById(bidIdx)
                            .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_BID_INVALID));

                    // 입찰 정보 수정
                    auctionBid.setAuctionBidPrice(auction.getUpperPrice());
                    auctionBid.setAuction(auction);
                } else { // 없으면
                    AuctionBid auctionBid = AuctionBidMapper.INSTANCE.registImToAuctionBid(user, auction, auction.getUpperPrice());

                    // 새로 등록
                    auctionBidRepository.save(auctionBid);
                }
            }

            // 경매 상태 거래중으로 변경
            auction.setAuctionStatus(AuctionStatus.IN_TRADE.getStatus());

            //입찰 상태를 낙찰 예정으로 변경
            AuctionBid bid = auctionBidRepository.findByUser_UserIdxAndAuction_AuctionIdx(auction.getHighestBidUser().getUserIdx(), auctionIdx)
                    .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_BID_INVALID));

            bid.setAuctionBidStatus(BidStatus.EXPECTED.getStatus());

            // 판매자의 계좌번호와 은행 전달
            response = new AuctionTradeResponse(auction.getUser().getUsername() ,auction.getUser().getUserAccount(),
                    auction.getUser().getUserAccountBank());
        }
        else {
            throw new CustomException(CustomExceptionStatus.ALREADY_TRADE_AUCTION);
        }

        return response;
    }

    // 경매 거래 취소
    @Override
    public void cancelAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

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

        auctionBidRepository.deleteByUser_UserIdxAndAuction_AuctionIdx(auctionBidList.get(0).getUser().getUserIdx(), auctionIdx);
        log.info("거래 취소 완료");
    }

    // 경매 거래 확정
    @Override
    public void confirmAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));
        Gifticon gifticon = gifticonRepository.findById(auction.getGifticon().getGifticonIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));
        User user = userRepository.findById(auction.getHighestBidUser().getUserIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));

        auction.setAuctionStatus(AuctionStatus.AWARDED.getStatus());

        for(AuctionBid bid : auction.getAuctionBidList()) {
            if(bid.getUser().getUserIdx().equals(user.getUserIdx())){
                bid.setAuctionBidStatus(BidStatus.AWARDED.getStatus());
            }
            else {
                bid.setAuctionBidStatus(BidStatus.FAILURE.getStatus());
            }
        }

        gifticon.setUser(user);
        gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
        auction.setAuctionCompletedDate(LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    public AuctionConfirmResponse getConfirmAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

        auction.setNotificationCreatedDate(LocalDateTime.now());

        AuctionConfirmResponse response = AuctionMapper.INSTANCE.auctionToConfirm(auction);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public AuctionConfirmBuyResponse getConfirmBuyAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

        auction.setNotificationCreatedDate(LocalDateTime.now());

        AuctionConfirmBuyResponse response = AuctionMapper.INSTANCE.auctionToConfirmBuy(auction);

        return response;
    }


    public List<Auction> getAuctionConfirmList() {
        List<Auction> auctions = auctionRepository.findByAuctionListConfirm();

        return auctions;
    }

    @Override
    public List<Auction> getAuctionExpiredList() {
        return auctionRepository.findAuctionAllExpired();
    }

    @Override
    public void rejectAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));
        Gifticon gifticon = gifticonRepository.findById(auction.getGifticon().getGifticonIdx())
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));

        auction.setAuctionCompletedDate(auction.getAuctionEndDate());
        auction.setAuctionStatus(AuctionStatus.EXPIRED.getStatus());
        gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
    }

    @Override
    public List<Auction> getPopularAuction() {
        List<Auction> auctions = auctionRepository.findAuctionList();

        List<Auction> auctionList = new ArrayList<>();
        auctionList.add(auctions.get(0));
        auctionList.add(auctions.get(1));

        return auctionList;
    }

    @Override
    public List<Integer> getMainCategory() {
        List<Auction> auctions = auctionRepository.findAwardedAuctionList();

        int[] mainCategoryCount = new int[6];

        for(Auction auction : auctions) {
            int idx = auction.getGifticon().getMainCategory().getMainCategoryIdx();
            mainCategoryCount[idx]++;
        }

        int maxIdx = 1;
        for(int i = 1; i < 6; i++) {
            if(mainCategoryCount[i] > mainCategoryCount[maxIdx]){
                maxIdx = i;
            }
        }

        int secondIdx = 2;
        for(int i = 1; i < 6; i++) {
            if(maxIdx != i && mainCategoryCount[i] > mainCategoryCount[maxIdx]){
                secondIdx = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(maxIdx);
        list.add(secondIdx);

        return list;
    }

    @Override
    public List<Integer> getSubCategory() {
        List<Auction> auctions = auctionRepository.findAwardedAuctionList();

        int[] subCategoryCount = new int[11];

        for(Auction auction : auctions) {
            int idx = auction.getGifticon().getSubCategory().getSubCategoryIdx();
            subCategoryCount[idx]++;
        }

        int maxIdx = 1;
        for(int i = 1; i < 11; i++) {
            if(subCategoryCount[i] > subCategoryCount[maxIdx]){
                maxIdx = i;
            }
        }

        int secondIdx = 2;
        for(int i = 1; i < 11; i++) {
            if(maxIdx != i && subCategoryCount[i] > subCategoryCount[maxIdx]){
                secondIdx = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(maxIdx);
        list.add(secondIdx);

        return list;
    }

}
