package com.zerobase.zerolms.product.model;

import lombok.Data;

@Data
public class ProductInput {

    long id;
    long categoryId;
    String subject;
    String keyword;
    String contents;
    long price;
    long salePrice;
    String saleEnDtText;

    // 삭제를 위한
    String idList;
}
