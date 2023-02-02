package com.zerobase.zerolms.product.model;

import com.zerobase.zerolms.admin.model.CommonParam;
import lombok.Data;

@Data
public class ProductParam extends CommonParam {

    long id; // product.id
    String email;
    long categoryId;

}
