package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.sale.dto.request.ModifySaleRequest;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import com.conseller.conseller.sale.dto.request.SaleListRequest;
import com.conseller.conseller.sale.dto.response.CommonResponse;
import com.conseller.conseller.sale.dto.response.DetailSaleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;

    private final String SUCCESS = "success";

    // 판매 목록
    @PostMapping
    public ResponseEntity<List<Sale>> getSaleList(@RequestBody SaleListRequest request) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // 판매 글 등록
    @PostMapping("/regist")
    public ResponseEntity<CommonResponse> registSale(@RequestBody RegistSaleRequest request) {
//        saleService.registSale(request);

        return new ResponseEntity<>(new CommonResponse(true, SUCCESS), HttpStatus.OK);
    }

    // 판매 글 상세보기
    @GetMapping("/{sale_idx}")
    public ResponseEntity<DetailSaleResponse> detailSale(@PathVariable("sale_idx") Long saleIdx) {
        DetailSaleResponse detailSaleResponse = saleService.detailSale(saleIdx);

        return new ResponseEntity<>(detailSaleResponse , HttpStatus.OK);
    }

    // 판매 글 수정
    @PutMapping
    public ResponseEntity<CommonResponse> modifySale(@RequestBody ModifySaleRequest saleRequest) {
        saleService.modifySale(saleRequest);
        return new ResponseEntity<>(new CommonResponse(true, SUCCESS), HttpStatus.OK);
    }

    // 판매 글 삭제
    @DeleteMapping("/{sale_idx}")
    public ResponseEntity<CommonResponse> deleteSale(@PathVariable("sale_idx") Long saleIdx) {
        saleService.deleteSale(saleIdx);
        return new ResponseEntity<>(new CommonResponse(true, SUCCESS), HttpStatus.OK);
    }

    // 판매 상태 변경
    @PutMapping("/{sale_idx}/status")
    public ResponseEntity<CommonResponse> saleStatusPermute(@PathVariable("sale_idx") Long saleIdx) {
        saleService.saleStatusPermute(saleIdx);
        return new ResponseEntity<>(new CommonResponse(true, SUCCESS), HttpStatus.OK);
    }


}
