package com.conseller.conseller.sale.dto.mapper;

import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface SaleMapper {
    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    //RegistSaleRequest -> Sale 매핑
    Sale registSaleRequestToSale(RegistSaleRequest request);

}
