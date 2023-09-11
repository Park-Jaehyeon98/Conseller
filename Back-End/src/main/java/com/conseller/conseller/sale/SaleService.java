package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import com.conseller.conseller.sale.dto.response.DetailSaleResponse;
import com.conseller.conseller.sale.enums.SaleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;

    public List<Sale> getSaleList() {

        return null;
    }

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

    public DetailSaleResponse detailSale(Long saleIdx) {
        Sale sale = saleRepository.findById(saleIdx).orElseThrow();

        User user = userRepository.findById(sale.getUser().getUserIdx()).orElseThrow();

        DetailSaleResponse response = DetailSaleResponse.builder()
                .saleUserIdx(user.getUserIdx())
                .saleText(sale.getSaleText())
                .saleEndedDate(sale.getSaleEndedDate())
                .saleCreatedDate(sale.getSaleCreatedDate())
                .saleUserNickname(user.getUserNickname())
                .build();

        return null;
    }

}
