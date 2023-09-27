package com.conseller.conseller.gifticon.repository;

import com.conseller.conseller.entity.QGifticon;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.conseller.conseller.entity.QGifticon.gifticon;

@Repository
@RequiredArgsConstructor
public class GifticonRepositoryImpl {

    private final JPAQueryFactory jpaQueryFactory;

    //구현
    public List<Tuple> getUserIdxAndExpiringGifticonCount() {
        long oneDayInMillis = 24 * 60 * 60 * 1000;
        long currentDate = System.currentTimeMillis();

        return jpaQueryFactory
        .select(gifticon.user.userIdx).as("유저"),
                gifticon.gifticonEndDate.subtract(currentDate).divide(oneDayInMillis).as("남은 날짜"),
                gifticon.gifticonName.max().as("기프티콘 이름"),
                gifticon.gifticonName.count().as("동일 남은 날짜 기프티콘 개수"))
                .from(gifticon)
                .where(gifticon.gifticonEndDate.subtract(currentDate).divide(oneDayInMillis).in(1L, 7L))
                .groupBy(gifticon.userIdx, gifticon.gifticonEndDate.subtract(currentDate).divide(oneDayInMillis))
                .orderBy(gifticon.userIdx.asc(), gifticon.gifticonEndDate.subtract(currentDate).divide(oneDayInMillis).asc())
                .fetch();
    }
}
