package com.conseller.conseller.store;

import com.conseller.conseller.entity.Store;
import com.conseller.conseller.store.dto.request.StoreListRequest;
import com.conseller.conseller.store.enums.StoreStatus;
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

import static com.conseller.conseller.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl {
    private final JPAQueryFactory factory;

    public Page<Store> findStoreList(StoreListRequest request, Pageable pageable) {
        List<Store> content = factory
                .selectFrom(store)
                .where(
                        eqCategory(request.getMainCategory(), request.getSubCategory()),
                        eqSearch(request.getSearchQuery()),
                        store.storeStatus.eq(StoreStatus.IN_PROGRESS.getStatus())
                )
                .orderBy(orderSpecifier(request.getStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = factory
                .select(store.count())
                .from(store)
                .where(
                        eqCategory(request.getMainCategory(), request.getSubCategory()),
                        eqSearch(request.getSearchQuery()),
                        store.storeStatus.eq(StoreStatus.IN_PROGRESS.getStatus())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression eqCategory(Integer mainCategory, Integer subCategory) {
        if(subCategory != 0) {
            return store.gifticon.subCategory.subCategoryIdx.eq(subCategory);
        }else if(mainCategory != 0 && subCategory == 0) {
            return store.gifticon.mainCategory.mainCategoryIdx.eq(mainCategory);
        }else {
            return null;
        }
    }

    private BooleanExpression eqSearch(String searchQuery) {
        if(!StringUtils.hasText(searchQuery)){
            return null;
        }

        return store.gifticon.gifticonName.containsIgnoreCase(searchQuery);
    }

    private OrderSpecifier orderSpecifier(Integer status) {
        if(status == 0) {
            return new OrderSpecifier(Order.DESC, store.storeCreatedDate);
        } else if (status == 1) {
            return new OrderSpecifier(Order.ASC, store.gifticon.gifticonEndDate);
        } else  {
            return new OrderSpecifier(Order.ASC, store.storePrice);
        }
    }
}
