package com.conseller.conseller.store.dto.response;

import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.store.enums.StoreStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StoreResponse {
    private long storeIdx;

    private int storePrice;

    private String storeCreatedDate;

    private String storeEndDate;

    private String storeText;

    private String storeStatus;

    private

    private long gifticonIdx;

    private long consumerIdx;
}
