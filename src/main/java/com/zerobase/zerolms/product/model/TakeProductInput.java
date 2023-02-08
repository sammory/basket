package com.zerobase.zerolms.product.model;

import com.zerobase.zerolms.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeProductInput {

    long productId;
    String email;

    long takeProductId;

}
