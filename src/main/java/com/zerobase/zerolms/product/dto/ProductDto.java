package com.zerobase.zerolms.product.dto;

import com.zerobase.zerolms.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {

    Long id;
    long categoryId;
    String imagePath;
    String subject;
    String summary;
    String contents;
    long price;
    long salePrice;
    LocalDate saleEndDt;
    LocalDateTime regDt;  // 등록일
    LocalDateTime udtDt;  // 수정일

    String filename;
    String urlFilename;

    // 추가컬럼
    long totalCount;
    long seq;


    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .imagePath(product.getImagePath())
                .subject(product.getSubject())
                .summary(product.getSummary())
                .contents(product.getContents())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .saleEndDt(product.getSaleEndDt())
                .regDt(product.getRegDt())
                .udtDt(product.getUdtDt())
                .filename(product.getFilename())
                .urlFilename(product.getUrlFilename())
                .build();
    }
    public static List<ProductDto> of(List<Product> product) {

        if (product == null) {
            return null;
        }
        List<ProductDto> productList = new ArrayList<>();
        for (Product x : product) {
            productList.add(ProductDto.of(x));
        }
        return productList;

    /*
        if (product != null) {
            List<ProductDto> productList = new ArrayList<>();
            for (Product x : product) {
                productList.add(ProductDto.of(x));
            }
            return productList;
        }
        return null;

     */
    }
}
