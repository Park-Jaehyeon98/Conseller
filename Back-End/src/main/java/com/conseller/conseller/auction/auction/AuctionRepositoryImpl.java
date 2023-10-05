package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.request.AuctionListRequest;
import com.conseller.conseller.auction.auction.enums.AuctionStatus;
import com.conseller.conseller.entity.Auction;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.conseller.conseller.entity.QAuction.auction;

@Repository
@RequiredArgsConstructor
public class AuctionRepositoryImpl{
    private final JPAQueryFactory factory;

    public Page<Auction> findAuctionList(AuctionListRequest request, Pageable pageable) {
        List<Auction> content = factory
                .selectFrom(auction)
                .where(
                        eqCategory(request.getMainCategory(), request.getSubCategory()),
                        eqSearch(request.getSearchQuery()),
                        auction.auctionStatus.eq(AuctionStatus.IN_PROGRESS.getStatus())
                )
                .orderBy(orderSpecifier(request.getStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = factory
                .select(auction.count())
                .from(auction)
                .where(
                        eqCategory(request.getMainCategory(), request.getSubCategory()),
                        eqSearch(request.getSearchQuery()),
                        auction.auctionStatus.eq(AuctionStatus.IN_PROGRESS.getStatus())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression eqCategory(Integer mainCategory, Integer subCategory) {
        if(subCategory != 0) {
            return auction.gifticon.subCategory.subCategoryIdx.eq(subCategory);
        }else if(mainCategory != 0 && subCategory == 0) {
            return auction.gifticon.mainCategory.mainCategoryIdx.eq(mainCategory);
        }else {
            return null;
        }
    }

    private BooleanExpression eqSearch(String searchQuery) {
        if(!StringUtils.hasText(searchQuery)){
            return null;
        }

        return auction.gifticon.gifticonName.containsIgnoreCase(searchQuery);
    }

    private OrderSpecifier orderSpecifier(Integer status) {
        if(status == 0) {
            return new OrderSpecifier(Order.DESC, auction.auctionStartDate);
        } else if (status == 1) {
            return new OrderSpecifier(Order.ASC, auction.gifticon.gifticonEndDate);
        } else if(status == 2) {
            return new OrderSpecifier(Order.ASC, auction.auctionHighestBid);
        } else  {
            return new OrderSpecifier(Order.ASC, auction.upperPrice);
        }
    }
}
