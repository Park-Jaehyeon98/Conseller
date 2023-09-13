package com.conseller.conseller.sale;

import com.conseller.conseller.category.mainCategory.MainCategoryRepository;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.sale.dto.RegistSaleDto;
import com.conseller.conseller.sale.dto.request.ModifySaleRequest;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import com.conseller.conseller.sale.dto.request.SaleListRequest;
import com.conseller.conseller.sale.dto.response.DetailSaleResponse;
import com.conseller.conseller.sale.enums.SaleStatus;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    // 판매 목록
    public List<Sale> getSaleList(SaleListRequest saleListRequest) { //queryDSL 사용

        return null;
    }

    // 판매 글 등록
    @Transactional
    public void registSale(RegistSaleRequest request) {

        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new RuntimeException());
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonIdx())
                .orElseThrow(() -> new RuntimeException());

        RegistSaleDto saleDto = new RegistSaleDto(request, user, gifticon);

        Sale sale = saleDto.toEntity(saleDto);

        saleRepository.save(sale);
    }

    // 판매 글 상세보기
    public DetailSaleResponse detailSale(Long saleIdx) {
        Sale sale = saleRepository.findById(saleIdx)
                .orElseThrow(() -> new RuntimeException());

        User user = userRepository.findById(sale.getUser().getUserIdx())
                .orElseThrow(() -> new RuntimeException());

        DetailSaleResponse response = new DetailSaleResponse(user, sale);

        return response;
    }

    //판매 글 수정
    public void modifySale(Long saleIdx ,ModifySaleRequest saleRequest) {
        Sale sale = saleRepository.findById(saleIdx).orElseThrow(() -> new RuntimeException());

        sale.setSaleEndDate(saleRequest.getSaleEndDate());
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
