package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.sale.dto.request.ModifySaleRequest;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import com.conseller.conseller.sale.dto.response.DetailSaleResponse;
import com.conseller.conseller.sale.enums.SaleStatus;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;

    // 판매 목록
    public List<Sale> getSaleList() {

        return null;
    }

    // 판매 글 등록
    @Transactional
    public void registSale(RegistSaleRequest request) {

        User user = userRepository.findById(request.getUserIdx()).orElseThrow();
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx()).orElseThrow();

        Sale sale = Sale.builder()
                .user(user)
                .salePrice(request.getSalePrice())
                .saleText(request.getSaleText())
                .gifticon(gifticon)
                .saleStatus(SaleStatus.IN_PROGRESS)
                .build();

        saleRepository.save(sale);
    }

    // 판매 글 상세보기
    public DetailSaleResponse detailSale(Long saleIdx) {
        Sale sale = saleRepository.findById(saleIdx).orElseThrow(() -> new RuntimeException());

        User user = userRepository.findById(sale.getUser().getUserIdx()).orElseThrow(() -> new RuntimeException());

        DetailSaleResponse response = DetailSaleResponse.builder()
                .saleUserIdx(user.getUserIdx())
                .saleText(sale.getSaleText())
                .saleEndedDate(sale.getSaleEndedDate())
                .saleCreatedDate(sale.getSaleCreatedDate())
                .saleUserNickname(user.getUserNickname())
                .build();

        return response;
    }

    //판매 글 수정
    public void modifySale(ModifySaleRequest saleRequest) {
        Sale sale = saleRepository.findById(saleRequest.getSaleIdx()).orElseThrow(() -> new RuntimeException());

        sale.setSaleText(saleRequest.getSaleText());
    }

    // 판매 글 삭제
    public void deleteSale(Long saleIdx) {
        saleRepository.deleteById(saleIdx);
    }

    // 판매 상태 변경
    public void saleStatusPermute(Long saleIdx) {
        Sale sale = saleRepository.findById(saleIdx).orElseThrow(() -> new RuntimeException());

        sale.setSaleStatus(SaleStatus.IN_TRADE);
    }

}
