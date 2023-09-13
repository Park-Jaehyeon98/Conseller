package com.conseller.conseller.sale;

import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.sale.dto.request.ModifySaleRequest;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import com.conseller.conseller.sale.dto.request.SaleListRequest;
import com.conseller.conseller.sale.dto.response.DetailSaleResponse;

import java.util.List;

public interface SaleService {
    public List<Sale> getSaleList(SaleListRequest request);

    public void registSale(RegistSaleRequest request);

    public DetailSaleResponse detailSale(Long saleIdx);

    public void modifySale(Long saleIdx ,ModifySaleRequest saleRequest);

    public void deleteSale(Long saleIdx);

    public void saleStatusPermute(Long saleIdx);

}
