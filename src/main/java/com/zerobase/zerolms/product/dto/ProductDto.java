package com.zerobase.zerolms.product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    Long id;
    String imagePath;
    String subject;
    String contents;
    long price;
    long samePrice;
    LocalDateTime saleEndDt;
    LocalDateTime regDt;  // 등록일
    LocalDateTime udtDt;  // 수정일

    // 추가컬럼
    long totalCount;
    long seq;
}
