package com.zerobase.zerolms.basket.service;

import com.zerobase.zerolms.basket.dto.BasketDto;
import com.zerobase.zerolms.basket.model.BasketInput;
import com.zerobase.zerolms.basket.model.BasketParam;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ServiceResult;

import java.util.List;

public interface BasketService {

    /**
     * 장바구니 담기
     */
    ServiceResult add(BasketInput parameter);

    /**
     * 내 장바구니 상품 삭제
     */
    boolean del(long id);

    /**
     * 전체 상품 목록
     */
    List<ProductDto> listAll();

    /**
     * 내 장바구니 목록
     */
    List<BasketDto> myBasket(String email);


}
