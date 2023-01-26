package com.zerobase.zerolms.product.service;

import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductInput;
import com.zerobase.zerolms.product.model.ProductParam;

import java.util.List;

public interface ProductService {

    /**
     * 상품 등록
     */
    boolean add(ProductInput parameter);

    /**
     * 상품 목록
     */
    List<ProductDto> list(ProductParam parameter);

}
