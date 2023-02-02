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
     * 상품 정보수정
     */
    boolean set(ProductInput parameter);

    /**
     * 상품 목록
     */
    List<ProductDto> list(ProductParam parameter);

    /**
     * 상품 상세정보
     */
    ProductDto getById(long id);

    /**
     * 상품 내용 삭제
     */
    boolean del(String idList);

    /**
     * 프론트 상품 목록
     */
    List<ProductDto> frontList(ProductParam parameter);

    /**
     * 프론트 상품 상세 정보
     */
    ProductDto frontDetail(long id);
}
