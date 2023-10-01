package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.request.BarterFilterDto;
import com.conseller.conseller.barter.barter.enums.BarterStatus;
import com.conseller.conseller.entity.Barter;
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

import static com.conseller.conseller.entity.QBarter.barter;

@Repository
@RequiredArgsConstructor
public class BarterRepositoryImpl {
    private final JPAQueryFactory factory;

    public Page<Barter> findBarterList(BarterFilterDto filter, Pageable pageable) {
        List<Barter> content  = factory
                .selectFrom(barter)
                .where(
                        eqCategory(filter.getMainCategory(), filter.getSubCategory()),
                        eqSearch(filter.getSearchQuery()),
                        barter.barterStatus.in(BarterStatus.EXCHANGEABLE.getStatus(), BarterStatus.SUGGESTED.getStatus())
                )
                .orderBy(orderSpecifier(filter.getStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = factory
                .select(barter.count())
                .from(barter)
                .where(
                        eqCategory(filter.getMainCategory(), filter.getSubCategory()),
                        eqSearch(filter.getSearchQuery()),
                        barter.barterStatus.in(BarterStatus.EXCHANGEABLE.getStatus(), BarterStatus.SUGGESTED.getStatus())
                )
                .fetchOne();
        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression eqCategory(Integer mainCategory, Integer subCategory) {
        if(mainCategory == 0 && subCategory == 0) {
            return null;
        }else if(mainCategory != 0 && subCategory == 0) {
            return barter.subCategory.mainCategory.mainCategoryIdx.eq(mainCategory);
        }else {
            return barter.subCategory.subCategoryIdx.eq(subCategory);
        }
    }

    private BooleanExpression eqSearch(String searchQuery) {
        if(!StringUtils.hasText(searchQuery)){
            return null;
        }

        return barter.barterName.containsIgnoreCase(searchQuery);
    }

    private OrderSpecifier orderSpecifier(Integer status) {

        if(status == 1) {
            return new OrderSpecifier(Order.ASC, barter.barterEndDate);
        } else {
            return new OrderSpecifier(Order.DESC, barter.barterCreatedDate);
        }
    }
    // 기본: 시작일자가 느린 순서 오래된게 나중에 - 최신순
    // 끝나는 일자가 빠른 순서 - 마감임박순
}
