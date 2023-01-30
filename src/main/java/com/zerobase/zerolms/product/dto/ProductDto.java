package com.zerobase.zerolms.product.dto;

import com.zerobase.zerolms.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {

    Long id;
    long categoryId;
    String imagePath;
    String subject;
    String keyword;
    String contents;
    long price;
    long salePrice;
    LocalDate saleEndDt;
    LocalDateTime regDt;  // 등록일
    LocalDateTime udtDt;  // 수정일

    // 추가컬럼
    long totalCount;
    long seq;

    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .imagePath(product.getImagePath())
                .subject(product.getSubject())
                .keyword(product.getKeyword())
                .contents(product.getContents())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .saleEndDt(product.getSaleEndDt())
                .regDt(product.getRegDt())
                .udtDt(product.getUdtDt())
                .build();
    }
}
