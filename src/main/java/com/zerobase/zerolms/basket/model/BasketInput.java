package com.zerobase.zerolms.basket.model;

import lombok.Data;

@Data
public class BasketInput {

    long productId;
    String email;

    long basketId;

}
