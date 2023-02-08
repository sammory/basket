package com.zerobase.zerolms.product.service;

import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.dto.TakeProductDto;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.model.TakeProductParam;

import java.util.List;

public interface TakeProductService {

    /**
     * 구매요청 목록
     */
    List<TakeProductDto> list(TakeProductParam parameter);

    /**
     * 구매 상세 정보
     */
    TakeProductDto detail(long id);

    /**
     * 구매요청 상태변경
     */
    ServiceResult updateStatus(long id, String status);

    /**
     * 내 구매요청 목록
     */
    List<TakeProductDto> myProduct(String email);

    /**
     * 내 구매요청 취소처리
     */
    ServiceResult cancel(long Id);
}
