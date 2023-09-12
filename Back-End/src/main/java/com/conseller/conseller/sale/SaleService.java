package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
//    private final UserRepository userRepository;
//    private final GifticonRepository gifticonRepository;

    public List<Sale> getSaleList() {

        return null;
    }

//    public void registSale(RegistSaleRequest request) {
//
//        User user = userRepository.findById(request.getUserIdx()).orElseThrow();
//        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx()).orElseThrow();
//
//        Sale sale = Sale.builder()
//                .user(user)
//                .salePrice(request.getSalePrice())
//                .saleText(request.getSaleText())
//                .gifticon(gifticon)
//                .saleStatus(SaleStatus.IN_PROGRESS)
//                .build();
//
//        saleRepository.save(sale);
//    }

//    public DetailSaleResponse detailSale(Long saleIdx) {
//        Sale sale = saleRepository.findById(saleIdx).orElseThrow(() -> new RuntimeException());
//
//        User user = userRepository.findById(sale.getUser().getUserIdx()).orElseThrow(() -> new RuntimeException());
//
//        DetailSaleResponse response = DetailSaleResponse.builder()
//                .saleUserIdx(user.getUserIdx())
//                .saleText(sale.getSaleText())
//                .saleEndedDate(sale.getSaleEndedDate())
//                .saleCreatedDate(sale.getSaleCreatedDate())
//                .saleUserNickname(user.getUserNickname())
//                .build();
//
//        return response;
//    }

    public void deleteSale(Long saleIdx) {
        saleRepository.deleteById(saleIdx);
    }



}
