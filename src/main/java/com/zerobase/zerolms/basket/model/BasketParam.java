package com.zerobase.zerolms.basket.model;

import com.zerobase.zerolms.admin.model.CommonParam;
import lombok.Data;

@Data
public class BasketParam extends CommonParam {

    long id; // basket.id
    String email;
    long categoryId;

}
