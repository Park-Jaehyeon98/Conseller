package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Sale;
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
    @PostMapping
    public ResponseEntity<List<Sale>> getSaleList(@RequestBody SaleListRequest request) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/regist")
    public ResponseEntity<CommonResponse> registSale(@RequestBody RegistSaleRequest request) {
//        saleService.registSale(request);

        return new ResponseEntity<>(new CommonResponse(true, SUCCESS), HttpStatus.OK);
    }

//    @GetMapping("/{sale_idx}")
//    public ResponseEntity<DetailSaleResponse> detailSale(@PathVariable("sale_idx") Long saleIdx) {
////        DetailSaleResponse detailSaleResponse = saleService.detailSale(saleIdx);
//
////        return new ResponseEntity<>(detailSaleResponse , HttpStatus.OK);
//    }

    @DeleteMapping("/{sale_idx}")
    public ResponseEntity<CommonResponse> deleteSale(@PathVariable("sale_idx") Long saleIdx) {

        return new ResponseEntity<>(new CommonResponse(true, SUCCESS), HttpStatus.OK);
    }

    @PutMapping("/{sale_idx}/status")
    public ResponseEntity<CommonResponse> saleStatusPermute(@PathVariable("sale_idx") Long saleIdx) {

        return new ResponseEntity<>(new CommonResponse(true, SUCCESS), HttpStatus.OK);
    }

}
