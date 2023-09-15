package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.mapper.AuctionMapper;
import com.conseller.conseller.auction.auction.dto.request.AuctionListRequest;
import com.conseller.conseller.auction.auction.dto.request.ModifyAuctionRequest;
import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.DetailAuctionResponse;
import com.conseller.conseller.auction.bid.AuctionBidRepository;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<Auction> getAuctionList(AuctionListRequest request) {
        return null;
    }

    // 경매 글 등록
    @Override
    public void registAuction(RegistAuctionRequest request) {
        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new RuntimeException());
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx())
                .orElseThrow(() -> new RuntimeException());

        Auction auction = AuctionMapper.INSTANCE.registAuctionRequestToAuction(request, user, gifticon);

        auctionRepository.save(auction);
    }

    // 경매 글 상세보기
    @Override
    @Transactional(readOnly = true)
    public DetailAuctionResponse detailAuction(Long auctionIdx) {
        Auction auction = auctionRepository.findById(auctionIdx)
                .orElseThrow(() -> new RuntimeException());
        User user = userRepository.findById(auction.getUser().getUserIdx())
                .orElseThrow(() -> new RuntimeException());

        DetailAuctionResponse detailAuctionResponse = AuctionMapper.INSTANCE.entityToDetailAuctionResponse(user, auction, auction.getAuctionBidList());

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
}
