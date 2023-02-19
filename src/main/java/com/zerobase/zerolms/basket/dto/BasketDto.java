package com.zerobase.zerolms.basket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BasketDto {

    Long id;
    long productId;
    long quantity;
    long payPrice; // 결재금액
    long totalPay; // 장바구니 전체 결제금액


    LocalDateTime regDt; // 장바구니 담은 날짜

    // JOIN
    String userName;
    String phone;
    String subject;

    // 추가컬럼
    long totalCount;
    long seq;

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }

}
