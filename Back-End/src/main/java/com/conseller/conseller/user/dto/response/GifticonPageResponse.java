package com.conseller.conseller.user.dto.response;

import com.conseller.conseller.gifticon.dto.response.GifticonData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GifticonPageResponse {
    private Long totalElements;
    private Integer totalPages;
    private List<GifticonData> items;

    @Builder
    public GifticonPageResponse(Long totalElements, Integer totalPages, List<GifticonData> items) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.items = items;
    }
}
