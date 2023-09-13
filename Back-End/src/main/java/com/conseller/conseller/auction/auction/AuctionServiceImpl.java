package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuctionServiceImpl implements AuctionService{
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;

    @Override
    public void registAuction(RegistAuctionRequest request) {
        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new RuntimeException());
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx())
                .orElseThrow(() -> new RuntimeException());

        request.setGifticon(gifticon);
        request.setUser(user);

        Auction auction = request.toEntity(request);

        auctionRepository.save(auction);
    }
}
