package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.mapper.AuctionMapper;
import com.conseller.conseller.auction.auction.dto.request.AuctionListRequest;
import com.conseller.conseller.auction.auction.dto.request.ModifyAuctionRequest;
import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.*;
import com.conseller.conseller.auction.auction.enums.AuctionStatus;
import com.conseller.conseller.auction.bid.AuctionBidRepository;
import com.conseller.conseller.auction.bid.enums.BidStatus;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.repository.GifticonRepository;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.utils.DateTimeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

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

        auction.setAuctionEndDate(DateTimeConverter.getInstance().convertLocalDateTime(auctionRequest.getAuctionEndDate()));
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
    public AuctionTradeResponse tradeAuction(Long auctionIdx, Integer index) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.AUCTION_INVALID));

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

        //여기서 유저가 같으니 다 삭제됨
        auctionBidRepository.deleteByUser_UserIdxAndAuction_AuctionIdx(auctionBidList.get(0).getUser().getUserIdx(), auctionIdx);

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
}
