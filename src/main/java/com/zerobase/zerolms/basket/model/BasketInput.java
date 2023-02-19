package com.zerobase.zerolms.basket.model;

import lombok.Data;

@Data
public class BasketInput {

    long id;
    long productId;
    String email;
    long quantity;

    long basketId;

}
