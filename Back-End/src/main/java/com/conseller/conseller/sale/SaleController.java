package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.sale.dto.request.ModifySaleRequest;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import com.conseller.conseller.sale.dto.request.SaleListRequest;
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


        return ResponseEntity.ok()
                .body(null);
    }

    // 판매 글 등록
    @PostMapping("/regist")
    public ResponseEntity<Object> registSale(@RequestBody RegistSaleRequest request) {
        saleService.registSale(request);

        return ResponseEntity.ok()
                .build();
    }

    // 판매 글 상세보기
    @GetMapping("/{sale_idx}")
    public ResponseEntity<DetailSaleResponse> detailSale(@PathVariable("sale_idx") Long saleIdx) {
        DetailSaleResponse detailSaleResponse = saleService.detailSale(saleIdx);

        return new ResponseEntity<>(detailSaleResponse , HttpStatus.OK);
    }

    // 판매 글 수정
    @PutMapping("/{sale_idx}")
    public ResponseEntity<Object> modifySale(@PathVariable("sale_idx") Long saleIdx, @RequestBody ModifySaleRequest saleRequest) {
        saleService.modifySale(saleIdx, saleRequest);
        return ResponseEntity.ok()
                .build();
    }

    // 판매 글 삭제
    @DeleteMapping("/{sale_idx}")
    public ResponseEntity<Object> deleteSale(@PathVariable("sale_idx") Long saleIdx) {
        saleService.deleteSale(saleIdx);
        return ResponseEntity.ok()
                .build();
    }

    // 판매 상태 변경
    @PutMapping("/{sale_idx}/status")
    public ResponseEntity<Object> saleStatusPermute(@PathVariable("sale_idx") Long saleIdx) {
        saleService.saleStatusPermute(saleIdx);
        return ResponseEntity.ok()
                .build();
    }


}
