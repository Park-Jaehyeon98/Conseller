package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import com.conseller.conseller.sale.dto.request.SaleListRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> registSale(@RequestBody RegistSaleRequest request) {
        saleService.registSale(request);

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }


}
